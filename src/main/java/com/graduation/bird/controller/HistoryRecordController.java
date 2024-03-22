package com.graduation.bird.controller;

import com.graduation.bird.entity.HistoryRecord;
import com.graduation.bird.entity.Result;
import com.graduation.bird.service.HistoryRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryRecordController {

    @Autowired
    private HistoryRecordService historyRecordService;

    //获取所有历史记录
    @PostMapping("/getAllHistoryRecord")
    public Result<List<HistoryRecord>> getAllHistoryRecord() {
        return Result.success(historyRecordService.getAllHistoryRecord());
    }

    //根据UID获取历史记录
    @PostMapping("/getAllHistoryRecordByUID")
    public Result<List<HistoryRecord>> getAllHistoryRecordByUID(String UID) {
        return Result.success(historyRecordService.getAllHistoryRecordByUID(UID));
    }

    //添加历史记录
    @PostMapping("/addHistoryRecord")
    public Result addHistoryRecord(HistoryRecord historyRecord) {
        return Result.success(historyRecordService.addHistoryRecord(historyRecord));
    }

    //删除历史记录
    @PostMapping("/deleteHistoryRecord")
    public Result deleteHistoryRecord(String UID, Long id) {
        return Result.success(historyRecordService.deleteHistoryRecord(UID, id));
    }


}
