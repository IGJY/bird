package com.graduation.bird.controller;

import com.graduation.bird.entity.PredictResult;
import com.graduation.bird.entity.Result;
import com.graduation.bird.entity.UploadModelResult;
import com.graduation.bird.service.ModelService;
import com.graduation.bird.taskmanager.TaskManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(produces = "application/json; charset=UTF-8")
public class ModelController {

    @Autowired
    private ModelService modelService;

    @Autowired
    private MappingJackson2HttpMessageConverter jsonConverter;

    @Autowired
    private TaskManager taskManager;

    //预测接口
    @PostMapping("/recognize-audio")
    public Result recognizeAudio(MultipartFile file) {


        String result = null;

        try{

            result = modelService.recognizeAudio(file);

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

    //模型文件上传接口
    @PostMapping("/upload-model")
    public Result uploadModel(MultipartFile file) {
        String result;

        try {
            result = modelService.uploadModel(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 调用 jsonConverter 的 readValue 方法将 JSON 字符串转换为 UploadModelResult 对象
        UploadModelResult uploadModelResult;
        try {
            uploadModelResult = jsonConverter.getObjectMapper().readValue(result, UploadModelResult.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse upload-model result", e);
        }

        return Result.success(uploadModelResult);
    }

    //提取特征
    //启动提取特征任务
    @PostMapping("/extract-features")
    public Result extractFeatures() throws IOException {
//        CompletableFuture<String> result;
//        try {
//            result = modelService.extractAndSaveFeatures();;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        // 调用 jsonConverter 的 readValue 方法将 JSON 字符串转换为 UploadModelResult 对象
//        UploadModelResult uploadModelResult;
//        try {
//            uploadModelResult = jsonConverter.getObjectMapper().readValue(result.toString(), UploadModelResult.class);
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to parse upload-model result", e);
//        }
//
//        return Result.success(uploadModelResult);

        CompletableFuture<String> future = modelService.extractAndSaveFeatures();
        String taskId = taskManager.startTask(future);
        return Result.success(taskId);

    }

    //查询提取特征任务结果
    @PostMapping("/extract-features/result")
    public Result getFeatureExtractionResult(String taskId) {
        String result = taskManager.getTaskResult(taskId);
        if (result == null) {
            return Result.error("任务未完成或未找到");
        }

        // 调用 jsonConverter 的 readValue 方法将 JSON 字符串转换为 UploadModelResult 对象
        UploadModelResult uploadModelResult;
        try {
            uploadModelResult = jsonConverter.getObjectMapper().readValue(result, UploadModelResult.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse extract-features result", e);
        }

        return Result.success(uploadModelResult);
    }

    //保存鸟类音频文件到数据集文件夹中
    @PostMapping("/save-audio")
    public Result saveAudio(MultipartFile file, String birdNumber) {
        String result;
        try {
            result = modelService.saveAudio(file,birdNumber);;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 调用 jsonConverter 的 readValue 方法将 JSON 字符串转换为 UploadModelResult 对象
        UploadModelResult uploadModelResult;
        try {
            uploadModelResult = jsonConverter.getObjectMapper().readValue(result, UploadModelResult.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse save-audio result", e);
        }

        return Result.success(uploadModelResult);

    }

    //复制npy文件，用新的训练集替换原来的训练集
    @PostMapping("/copy_MFCC")
    public Result copyMfcc() {
        String result;
        try {
            result = modelService.copyMfcc();;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 调用 jsonConverter 的 readValue 方法将 JSON 字符串转换为 UploadModelResult 对象
        UploadModelResult uploadModelResult;
        try {
            uploadModelResult = jsonConverter.getObjectMapper().readValue(result, UploadModelResult.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse copy_MFCC result", e);
        }

        return Result.success(uploadModelResult);

    }

}
