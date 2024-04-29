package com.graduation.bird.controller;

import com.graduation.bird.entity.PredictResult;
import com.graduation.bird.entity.Result;
import com.graduation.bird.service.AudioRecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping(produces = "application/json; charset=UTF-8")
public class AudioRecognitionController {

    @Autowired
    private AudioRecognitionService audioRecognitionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jsonConverter;

    @PostMapping("/recognize-audio")
    public Result recognizeAudio(MultipartFile file) {


        String result = null;

        try{

            result = audioRecognitionService.recognizeAudio(file);

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

        // 调用 jsonConverter 的 readValue 方法将 JSON 字符串转换为 PredictResult 对象
        PredictResult predictResult;
        try {
            predictResult = jsonConverter.getObjectMapper().readValue(result, PredictResult.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse prediction result", e);
        }

//        // 调用 JsonUtils 中的方法将 JSON 字符串转换为 PredictResult 对象
//        PredictResult predictResult = JsonUtils.parsePredictResult(result);

        return Result.success(predictResult);

    }

}
