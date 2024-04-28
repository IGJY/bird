package com.graduation.bird.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AudioRecognitionService {
    String recognizeAudio(String filePath);
}
