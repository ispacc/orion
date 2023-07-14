package io.ispacc.orion.admin.module.admin.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文件上传返回结果
 */
@Data
@EqualsAndHashCode
public class MinioUploadDto {
    private String url;
    private String name;
}
