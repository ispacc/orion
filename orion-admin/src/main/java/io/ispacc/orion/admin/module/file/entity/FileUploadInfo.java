package io.ispacc.orion.admin.module.file.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 文件上传信息表 总
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-15 17:31
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("file_upload_info")
public class FileUploadInfo extends Model<FileUploadInfo> {
    @TableId
    private Long id;

    private String fileInfoId;

    private Integer fileChunkNum;

    private Integer fileChunkSize;

    private String localUrl;

    private LocalDateTime createTime;
}
