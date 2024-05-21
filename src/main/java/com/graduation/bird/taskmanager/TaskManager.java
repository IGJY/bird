package com.graduation.bird.taskmanager;

import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CompletableFuture;

@Service
public class TaskManager {
    private final ConcurrentHashMap<String, CompletableFuture<String>> tasks = new ConcurrentHashMap<>();

    public String startTask(CompletableFuture<String> future) {
        String taskId = generateTaskId();
        tasks.put(taskId, future);
        return taskId;
    }

    public String getTaskStatus(String taskId) {
        CompletableFuture<String> future = tasks.get(taskId);
        if (future == null) {
            return "not_found";
        }
        if (future.isDone()) {
            try {
                future.get(); // 检查任务是否成功完成
                return "completed";
            } catch (Exception e) {
                return "failed";
            }
        }
        return "running";
    }

    public String getTaskResult(String taskId) {
        CompletableFuture<String> future = tasks.get(taskId);
        if (future != null && future.isDone()) {
            try {
                return future.get();
            } catch (Exception e) {
                return "failed";
            }
        }
        return null;
    }

    private String generateTaskId() {
        return Long.toHexString(System.currentTimeMillis());
    }
}
