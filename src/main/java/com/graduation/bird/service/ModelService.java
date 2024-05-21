package com.graduation.bird.service;

import com.graduation.bird.entity.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface ModelService {
    String recognizeAudio(MultipartFile file) throws IOException;

    String uploadModel(MultipartFile file) throws IOException;


    CompletableFuture<String> extractAndSaveFeatures() throws IOException;

    String saveAudio(MultipartFile file, String birdNumber) throws IOException;

    String copyMfcc() throws IOException;
}
