package com.graduation.bird.mapper;

import com.graduation.bird.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    //根据id查询用户
    @Select("select * from user where id = #{id}")
    User findById(Long id);

    //根据UID查询用户
    @Select("select * from user where UID = #{UID}")
    User findByUID(String UID);

    //添加用户
    @Insert("INSERT INTO user (UID, create_time, update_time, user_type, password, nickname, intro, gender, phone_number) VALUES (#{UID}, #{createTime}, #{updateTime}, #{userType}, #{password}, #{nickname}, #{intro}, #{gender}, #{phoneNumber})")
    int addUser(User user);

    //根据UID删除用户
    @Delete("DELETE FROM user WHERE UID = #{UID}")
    int deleteUser(String UID);

    //根据UID更新用户
    @Update("UPDATE user SET password=#{password}, nickname=#{nickname}, intro=#{intro}, gender=#{gender}, phone_number=#{phoneNumber} WHERE UID=#{UID}")
    int updateUser(User user);




}
