package com.graduation.bird.service.impl;

import com.graduation.bird.entity.Result;
import com.graduation.bird.entity.User;
import com.graduation.bird.mapper.UserMapper;
import com.graduation.bird.service.UserService;
import com.graduation.bird.utils.JwtUtil;
import com.graduation.bird.utils.MD5Utils;
import com.graduation.bird.utils.MergeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //获取所有用户信息
    @Override
    public List<User> getAllUsers() {
        return userMapper.getAllUsers();
    }

    // 根据id查找用户
    @Override
    public User findById(Long id) {
        return userMapper.findById(id);
    }

    // 根据uid查找用户
    @Override
    public User findByUID(String UID) {
        return userMapper.findByUID(UID);
    }

    //根据手机号查找用户
    @Override
    public User findByPhoneNumber(String phoneNumber) {
        return userMapper.findByPhoneNumber(phoneNumber);
    }

    // 添加用户
    @Override
    public Boolean addUser(User user) {
        // 对密码进行MD5加密
        user.setPassword(MD5Utils.encrypt(user.getPassword()));

        //使用UUID自动生成唯一UID
        user.setUID(UUID.randomUUID().toString().replace("-", ""));

        return userMapper.addUser(user);
    }

    //注册（默认为用户注册，管理员注册需要手动修改权限）
    @Override
    public Result register(User user) {

        //根据手机号判断用户是否已存在，如果存在则返回用户已存在
        if (findByPhoneNumber(user.getPhoneNumber()) != null) {

            return Result.error("用户已存在");

        } else {

            // 对密码进行MD5加密
            user.setPassword(MD5Utils.encrypt(user.getPassword()));

            //使用UUID自动生成唯一UID
            user.setUID(UUID.randomUUID().toString().replace("-", ""));

            //默认设置userType为user
            user.setUserType("user");

            return Result.success(userMapper.addUser(user));

        }

    }

    // 根据UID删除用户
    @Override
    public Boolean deleteUserByUID(String UID) {
        return userMapper.deleteUserByUID(UID);
    }

    //根据手机号删除用户
    @Override
    public Boolean deleteUserByPhoneNumber(String phoneNumber) {
        return userMapper.deleteUserByPhoneNumber(phoneNumber);
    }

    // 根据UID更新用户
    @Override
    public Result updateUser(User user, String oldPassword, String token) {

        //把用户输出来看一下
//        System.out.println(user);

        User originalUser = findByUID(user.getUID());

        //判断用户是否存在
        if (originalUser == null) {

            originalUser = findByPhoneNumber(user.getPhoneNumber());

            if (originalUser == null) {

                return Result.error("用户不存在");

            }

        }

        //如果用户有修改密码
        if (user.getPassword() != null) {

            //如果有了新密码就一定要输入旧密码
            if (oldPassword == null) {

                return Result.error("请填写密码");

            }

            //验证旧密码是否正确
            //TODO 测试
            if (!originalUser.getPassword().equals(MD5Utils.encrypt(oldPassword))) {

                return Result.error("密码错误");

            }

            //对新密码进行MD5加密
            user.setPassword(MD5Utils.encrypt(user.getPassword()));

        }

        // 使用工具类合并对象
        User mergedUser = MergeUtil.mergeObjects(originalUser, user);

        //更新用户信息
        Boolean isUpdated = userMapper.updateUser(mergedUser);

        //如果更新成功就把之前的旧token给删掉
        //改了密码才要重新登录
        if (isUpdated && user.getPassword() != null) {

            stringRedisTemplate.delete(token);
            System.out.println("old token删除成功");

        }

        return Result.success(isUpdated);

    }

    //登录
    @Override
    public Result login(String phoneNumber, String password) {

        //判断手机号和密码是否为空
        if (phoneNumber == null || password == null) {

            return Result.error("手机号或密码不能为空");

        } else {

            //根据手机号获取用户
            User user = findByPhoneNumber(phoneNumber);

            if (user == null) {

                return Result.error("用户不存在");

            } else {

                if (user.getPassword().equals(MD5Utils.encrypt(password))) {

                    //使用工具类生成token
                    Map<String, Object> claims = new HashMap<>();
                    claims.put("phoneNumber", user.getPhoneNumber());
                    claims.put("UID", user.getUID());
                    String token = JwtUtil.genToken(claims);

                    //把token存储到redis中,过期时间为3个小时
                    //直接既已token为key，也以token为value，这样在拦截器中就只判断能不能获取到就可以了
                    stringRedisTemplate.opsForValue().set(token, token, 3, TimeUnit.HOURS);

                    return Result.success(token);

                }
                return Result.error("密码错误");
            }
        }

    }

    @Override
    public User findByToken(Map<String, Object> claims) {

        //claims
        String phoneNumber = (String) claims.get("phoneNumber");
//        System.out.println("phoneNumber:" + phoneNumber);
        return findByPhoneNumber(phoneNumber);

    }

}
