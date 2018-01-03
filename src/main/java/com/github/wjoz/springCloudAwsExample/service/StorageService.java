package com.github.wjoz.springCloudAwsExample.service;

import com.amazonaws.services.s3.model.PutObjectResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StorageService {
    List<PutObjectResult> upload(MultipartFile[] multipartFiles);
}
