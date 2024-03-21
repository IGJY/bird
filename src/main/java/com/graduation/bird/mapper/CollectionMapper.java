package com.graduation.bird.mapper;

import com.graduation.bird.entity.Collection;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CollectionMapper {

    //查询所有收藏
    @Select("select * from collection")
    List<Collection> getAllCollection();

    //根据用户id查询所有收藏
    @Select("select * from collection where UID = #{UID}")
    List<Collection> getAllCollectionByUserId(Long userId);

    //添加收藏
    @Insert("INSERT INTO collection (UID, bird_id, bird_url) VALUES (#{UID}, #{birdId}, #{birdUrl})")
    Boolean addCollection(Collection collection);

    //删除收藏
    @Delete("DELETE FROM collection WHERE UID = #{UID} AND bird_id = #{birdId}")
    Boolean deleteCollection(String UID, Long birdId);

}
