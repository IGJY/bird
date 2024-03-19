package com.graduation.bird.mapper;

import com.graduation.bird.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    //获取所有用户信息
    @Select("select * from user")
    List<User> getAllUsers();

    //根据id查询用户
    @Select("select * from user where id = #{id}")
    User findById(Long id);

    //根据UID查询用户
    @Select("select * from user where UID = #{UID}")
    User findByUID(String UID);

    //添加用户
    @Insert("INSERT INTO user (UID, create_time, update_time, user_type, password, nickname, introduction, gender, phone_number) VALUES (#{UID}, now(), now(), #{userType}, #{password}, #{nickname}, #{introduction}, #{gender}, #{phoneNumber})")
    Boolean addUser(User user);

    //根据UID删除用户
    @Delete("DELETE FROM user WHERE UID = #{UID}")
    Boolean deleteUserByUID(String UID);

    //根据手机号删除用户
    @Delete("DELETE FROM user WHERE phone_number = #{phoneNumber}")
    Boolean deleteUserByPhoneNumber(String phoneNumber);

    //更新用户
    @Update("UPDATE user SET password=#{password}, nickname=#{nickname}, introduction=#{introduction}, gender=#{gender}, phone_number=#{phoneNumber}, update_time=now() WHERE UID=#{UID}")
    Boolean updateUser(User user);

    //根据手机号查找用户
    @Select("select * from user where phone_number = #{phoneNumber}")
    User findByPhoneNumber(String phoneNumber);


}
