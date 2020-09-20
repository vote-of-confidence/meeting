package io.voteofconfidence.meeting.service;

import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;

public interface FileUploader {

    ObjectWriteResponse putObject(PutObjectArgs args);
}
