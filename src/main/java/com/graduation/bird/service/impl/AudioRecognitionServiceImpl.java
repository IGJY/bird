package com.graduation.bird.service.impl;

import com.graduation.bird.service.AudioRecognitionService;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AudioRecognitionServiceImpl implements AudioRecognitionService {

//    @Value("${prediction.url}") // 预测接口的URL，可以通过配置文件注入
    private String predictionUrl = "http://10.181.6.181:5000/predict";
    @Override
    public String recognizeAudio(String filePath){
        // 创建请求体
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("file_path", filePath);

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        // 创建请求实体
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        // 发送 POST 请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(predictionUrl, HttpMethod.POST, entity, String.class);

        // 返回预测结果
        return response.getBody();
    }
}
