package com.github.wjoz.springCloudAwsExample.service.impl;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.github.wjoz.springCloudAwsExample.service.StorageService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AWSStorageService implements StorageService {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Autowired
    public AWSStorageService(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }

    public List<PutObjectResult> upload(MultipartFile[] multipartFiles) {
        List<PutObjectResult> putObjectResults = new ArrayList<>();

        Arrays.stream(multipartFiles)
                .filter(multipartFile -> !StringUtils.isEmpty(multipartFile.getOriginalFilename()))
                .forEach(multipartFile -> {
                    try {
                        putObjectResults.add(upload(multipartFile.getInputStream(), multipartFile.getOriginalFilename()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        return putObjectResults;
    }

    private PutObjectResult upload(InputStream inputStream, String uploadKey) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, uploadKey, inputStream, new ObjectMetadata());
        putObjectRequest.setCannedAcl(CannedAccessControlList.PublicRead);
        PutObjectResult putObjectResult = amazonS3Client.putObject(putObjectRequest);
        IOUtils.closeQuietly(inputStream);
        return putObjectResult;
    }
}
