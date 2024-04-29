package com.graduation.bird.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graduation.bird.entity.PredictResult;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public PredictResult parsePredictResult(String json) {
        try {
            // 将 JSON 字符串转换为 PredictResult 对象
            return objectMapper.readValue(json, PredictResult.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
