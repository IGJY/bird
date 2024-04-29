package com.graduation.bird.service;

import com.graduation.bird.entity.PredictResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface AudioRecognitionService {
    String recognizeAudio(MultipartFile file) throws IOException;
}
