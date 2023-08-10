package io.ispacc.orion.chat.module.file.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 文件上传信息表 分
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-15 17:31
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("file_upload")
public class FileUpload extends Model<FileUpload> {
    @TableId
    private Long id;

    private String fileUrl;

    private Integer fileChunk;

    private String fileMd5;

    private Long fileUploadInfoId;

    private LocalDateTime createTime;
}
