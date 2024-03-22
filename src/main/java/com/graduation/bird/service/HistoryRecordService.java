package com.graduation.bird.service;

import com.graduation.bird.entity.HistoryRecord;

import java.util.List;

public interface HistoryRecordService {

    //获取所有历史记录
    List<HistoryRecord> getAllHistoryRecord();

    //根据UID获取历史记录
    List<HistoryRecord> getAllHistoryRecordByUID(String UID);

    //添加历史记录
    Boolean addHistoryRecord(HistoryRecord historyRecord);

    //删除历史记录
    Boolean deleteHistoryRecord(String UID, Long id);

}
