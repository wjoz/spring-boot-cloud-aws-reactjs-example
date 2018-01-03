package com.github.wjoz.springCloudAwsExample.controller;

import com.amazonaws.services.s3.model.PutObjectResult;
import com.github.wjoz.springCloudAwsExample.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class UploadController {

    private final StorageService storageService;

    @Autowired
    public UploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public List<PutObjectResult> upload(@RequestParam("file") MultipartFile[] multipartFiles) {
        return storageService.upload(multipartFiles);
    }
}
