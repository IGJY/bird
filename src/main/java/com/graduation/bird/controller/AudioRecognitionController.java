package com.graduation.bird.controller;

import com.graduation.bird.service.AudioRecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(produces = "application/json; charset=UTF-8")
public class AudioRecognitionController {

    @Autowired
    private AudioRecognitionService audioRecognitionService;

    @PostMapping("/recognize-audio")
    public String recognizeAudio(String filePath) {
        return audioRecognitionService.recognizeAudio(filePath);
    }
}
