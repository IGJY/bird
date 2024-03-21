package com.graduation.bird.mapper;

import com.graduation.bird.entity.HistoryRecord;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface HistoryRecordMapper {

    // 查询所有历史记录
    @Select("select * from history_record")
    List<HistoryRecord> getAllHistoryRecord();

    // 根据用户ID查询历史记录
    @Select("select * from history_record where UID = #{UID}")
    List<HistoryRecord> getAllHistoryRecordByUserId(Long userId);

    // 添加历史记录
    @Insert("INSERT INTO history_record (UID, action_time, action, result, bird_url) VALUES (#{UID}, now(), #{action}, #{result}, #{birdUrl})")
    Boolean addHistoryRecord(HistoryRecord historyRecord);

    // 删除历史记录
    @Delete("DELETE FROM history_record WHERE UID = #{UID} AND id = #{id}")
    Boolean deleteHistoryRecord(String UID, Long id);

}
