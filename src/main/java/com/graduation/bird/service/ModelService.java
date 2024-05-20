package com.graduation.bird.service;

import com.graduation.bird.entity.Result;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ModelService {
    String recognizeAudio(MultipartFile file) throws IOException;

    String uploadModel(MultipartFile file) throws IOException;


    String  extractAndSaveFeatures() throws IOException;

    String saveAudio(MultipartFile file, String birdNumber) throws IOException;
}
