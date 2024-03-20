package com.graduation.bird.mapper;

import com.graduation.bird.entity.Birds;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BirdsMapper {

    //获取所有鸟类信息
    @Select("select * from birds")
    List<Birds> getAllBirds();

    //根据id查询鸟类信息
    @Select("select * from birds where id = #{id}")
    Birds findById(Long id);

    //添加鸟类
    @Insert("INSERT INTO birds (created_at, updated_at, morphology_and_features, name, habits, species, image_url, sound_url, description, growth_and_distribution) VALUES (now(), now(), #{morphologyAndFeatures}, #{name}, #{habits}, #{species}, #{imageUrl}, #{soundUrl}, #{description}, #{growthAndDistribution})")
    Boolean addBirds(Birds birds);

    //根据id删除鸟类
    @Delete("DELETE FROM birds WHERE id = #{id}")
    Boolean deleteBirds(Long id);

    //根据id更新鸟类
    @Update("UPDATE birds SET updated_at=now(), morphology_and_features=#{morphologyAndFeatures}, name=#{name}, habits=#{habits}, species=#{species}, image_url=#{imageUrl}, sound_url=#{soundUrl}, description=#{description}, growth_and_distribution=#{growthAndDistribution} WHERE id=#{id}")
    Boolean updateBirds(Birds birds);

}
