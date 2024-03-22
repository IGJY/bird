package com.graduation.bird.service.impl;

import com.graduation.bird.entity.HistoryRecord;
import com.graduation.bird.mapper.HistoryRecordMapper;
import com.graduation.bird.service.HistoryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryRecordServiceImpl implements HistoryRecordService {

    @Autowired
    private HistoryRecordMapper historyRecordMapper;

    //获取所有历史记录
    @Override
    public List<HistoryRecord> getAllHistoryRecord()
    {
        return historyRecordMapper.getAllHistoryRecord();
    }

    //根据UID获取所有历史记录
    @Override
    public List<HistoryRecord> getAllHistoryRecordByUID(String UID)
    {
        return historyRecordMapper.getAllHistoryRecordByUID(UID);
    }

    //添加历史记录
    @Override
    public Boolean addHistoryRecord(HistoryRecord historyRecord) {
        return historyRecordMapper.addHistoryRecord(historyRecord);
    }

    //删除历史记录
    @Override
    public Boolean deleteHistoryRecord(String UID, Long id) {
        return historyRecordMapper.deleteHistoryRecord(UID, id);
    }

}
