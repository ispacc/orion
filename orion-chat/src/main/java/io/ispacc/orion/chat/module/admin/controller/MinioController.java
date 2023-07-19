package io.ispacc.orion.chat.module.admin.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import io.ispacc.orion.chat.module.admin.dto.BucketPolicyConfigDto;
import io.ispacc.orion.chat.module.admin.dto.MinioUploadDto;
import io.ispacc.orion.common.api.CommonResult;
import io.minio.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Slf4j
@RequestMapping("/minio")
public class MinioController {

    @Value("${minio.endpoint}")
    private String ENDPOINT;
    @Value("${minio.bucketName}")
    private String BUCKET_NAME;
    @Value("${minio.accessKey}")
    private String ACCESS_KEY;
    @Value("${minio.secretKey}")
    private String SECRET_KEY;

    private MinioClient client;

    @PostConstruct
    public void init() {
        this.client = MinioClient.builder()
                .endpoint(ENDPOINT)
                .credentials(ACCESS_KEY, SECRET_KEY)
                .build();
    }

    @PostMapping("/file")
    public CommonResult<?> upload(@RequestPart("file") MultipartFile file) {
        try {
            checkBucketExists();

            String filename = file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            // Setting the storage object name
            String objectName = sdf.format(new Date()) + "/" + filename;
            uploadFileToBucket(file, objectName);
            log.info("File successfully uploaded!");

            MinioUploadDto minioUploadDto = new MinioUploadDto();
            minioUploadDto.setName(filename);
            minioUploadDto.setUrl(ENDPOINT + "/" + BUCKET_NAME + "/" + objectName);
            return CommonResult.success(minioUploadDto);

        } catch (Exception e) {
            log.info("An error occurred during upload: {}！", e.getMessage());
        }
        return CommonResult.failed();
    }

    private void checkBucketExists() throws Exception {
        boolean isExist = client.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build());
        if (!isExist) {
            createBucket();
        }
    }

    private void createBucket() throws Exception {
        // Creating bucket with read-only access
        client.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
        BucketPolicyConfigDto bucketPolicyConfigDto = createBucketPolicyConfigDto(BUCKET_NAME);
        SetBucketPolicyArgs setBucketPolicyArgs = SetBucketPolicyArgs.builder()
                .bucket(BUCKET_NAME)
                .config(JSONUtil.toJsonStr(bucketPolicyConfigDto))
                .build();
        client.setBucketPolicy(setBucketPolicyArgs);
    }

    private void uploadFileToBucket(MultipartFile file, String objectName) throws Exception {
        // Using putObject to upload a file to a bucket
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(BUCKET_NAME)
                .object(objectName)
                .contentType(file.getContentType())
                .stream(file.getInputStream(), file.getSize(), ObjectWriteArgs.MIN_MULTIPART_SIZE).build();
        client.putObject(putObjectArgs);
    }

    private BucketPolicyConfigDto createBucketPolicyConfigDto(String bucketName) {
        BucketPolicyConfigDto.Statement statement = BucketPolicyConfigDto.Statement.builder()
                .Effect("Allow")
                .Principal("*")
                .Action("s3:GetObject")
                .Resource("arn:aws:s3:::" + bucketName + "/*.**")
                .build();
        return BucketPolicyConfigDto.builder()
                .Version("2012-10-17")
                .Statement(CollUtil.toList(statement)).build();
    }

    @DeleteMapping("/file")
    public CommonResult<?> delete(@RequestParam("objectName") String objectName) {
        try {
            client.removeObject(RemoveObjectArgs.builder().bucket(BUCKET_NAME).object(objectName).build());
            return CommonResult.success(null);
        } catch (Exception e) {
            log.error("An error occurred during deletion: {}！", e.getMessage());
        }
        return CommonResult.failed();
    }
}
