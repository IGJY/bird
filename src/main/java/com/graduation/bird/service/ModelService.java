package com.graduation.bird.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ModelService {
    String recognizeAudio(MultipartFile file) throws IOException;

    String uploadModel(MultipartFile file) throws IOException;
}
