package io.voteofconfidence.meeting.service.impl;

import io.minio.*;
import io.minio.errors.*;
import io.voteofconfidence.meeting.service.FileUploader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class MinioServerFileUploader implements FileUploader {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MinioClient minioClient;

    @Override
    public ObjectWriteResponse putObject(PutObjectArgs args) {
        BucketExistsArgs bucketExistsArgs = BucketExistsArgs.builder()
                .bucket(args.bucket())
                .extraHeaders(args.extraHeaders())
                .extraQueryParams(args.extraQueryParams())
                .region(args.region())
                .build();

        ObjectWriteResponse response = null;
        boolean isExist = false;
        try {
            isExist = minioClient.bucketExists(bucketExistsArgs);
            if (isExist) {
                log.info("Bucket already exists.");
            } else {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(args.bucket()).build());
            }
            response = minioClient.putObject(args);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidBucketNameException | InvalidKeyException | InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException | XmlParserException | RegionConflictException e) {
           log.error(e.getMessage());
        }
        return response;
    }
}
