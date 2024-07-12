package com.fengwenyi.component.sample.example.controller;

import java.io.File;
import java.io.IOException;

import com.fengwenyi.api.result.ResultTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public ResultTemplate<?> upload(MultipartFile[] files) throws IllegalStateException, IOException {

        for (MultipartFile file : files) {
            saveFile(file);
        }

        return ResultTemplate.success();
    }

    private void saveFile(MultipartFile file) throws IOException {
        File destFile = new File("e:/temp/file/upload/" + file.getOriginalFilename());
        destFile.mkdirs();
        file.transferTo(destFile);
    }

}