package com.graduation.bird.service.impl;

import com.graduation.bird.entity.Result;
import com.graduation.bird.service.ModelService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {

//    @Value("${prediction.url}") // 预测接口的URL，可以通过配置文件注入
//    private String predictionUrl;

    // 设置预测接口的URL
    private final String predictionUrl = "http://localhost:5000/predict";

    // 设置模型上传接口的URL
    private final String uploadModelUrl = "http://localhost:5000/upload_model";

    // 设置特征提取接口的URL
    private final String FLASK_API_URL = "http://localhost:5000/save_MFCC";

    //设置保存鸟类音频文件到数据集文件夹的URL
    private final String SAVE_AUDIO_URL = "http://localhost:5000/save_audio";

    //设置复制npy文件的URL
    private final String COPY_MFCC_URL = "http://localhost:5000/copy_MFCC";

    //音频识别
    @Override
    public String recognizeAudio(MultipartFile file) throws IOException {

        //如果能够接收到文件就打印一下
//        System.out.println("接收到文件：" + file.getOriginalFilename());

        // 将 MultipartFile 转换为 File
        File tempFile = convertMultipartFileToFile(file);

        // 创建请求体，设置文件参数
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        });

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // 创建请求实体
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

        // 发送 POST 请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(predictionUrl, HttpMethod.POST, entity, String.class);

        // 删除临时文件
        tempFile.delete();

        // 返回预测结果
        return response.getBody();
    }

    //模型替换
    @Override
    public String uploadModel(MultipartFile file) throws IOException{

        // 将 MultipartFile 转换为 File
        File tempFile = convertMultipartFileToFile(file);

        // 创建请求体，设置文件参数
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        });

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // 创建请求实体
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

        // 发送 POST 请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(uploadModelUrl, HttpMethod.POST, entity, String.class);

        // 删除临时文件
        tempFile.delete();

        // 返回上传结果
        return response.getBody();
    }

    @Override
    public String extractAndSaveFeatures() throws IOException{
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(FLASK_API_URL, null, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                return "Failed to extract and save features: " + response.getStatusCode();
            }
        } catch (Exception e) {
            return "Error occurred: " + e.getMessage();
        }
    }

    //保存鸟类音频文件到数据集文件夹
    @Override
    public String saveAudio(MultipartFile file, String birdNumber) throws IOException {

        // 验证 birdNumber 是否为允许的编号
        List<String> allowedNumbers = Arrays.asList("0017", "0034", "0114", "0180", "0202", "0298", "0300", "0368", "0370", "1331");
        if (!allowedNumbers.contains(birdNumber)) {
            throw new IllegalArgumentException("Invalid bird number: " + birdNumber);
        }

        // 将 MultipartFile 转换为 File
        File tempFile = convertMultipartFileToFile(file);

        // 创建请求体，设置文件和编号参数
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new ByteArrayResource(file.getBytes()) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        });
        body.add("number", birdNumber);

        // 设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // 创建请求实体
        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

        // 发送 POST 请求
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(SAVE_AUDIO_URL, HttpMethod.POST, entity, String.class);

        // 删除临时文件
        tempFile.delete();

        // 返回保存结果
        return response.getBody();
    }

    //复制npy文件，用新的训练集替换原来的训练集
    @Override
    public String copyMfcc() throws IOException {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(COPY_MFCC_URL, null, String.class);
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody();
            } else {
                return "Failed to copy MFCC: " + response.getStatusCode();
            }
        } catch (Exception e) {
            return "Error occurred: " + e.getMessage();
        }
    }

    private File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File tempFile = File.createTempFile("uploaded_audio", ".wav");
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(file.getBytes());
        }
        return tempFile;
    }
}
