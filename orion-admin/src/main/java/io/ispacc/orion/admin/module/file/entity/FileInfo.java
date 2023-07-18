package io.ispacc.orion.admin.module.file.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.*;

import java.time.LocalDateTime;

/**
 * 文件存储表
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-15 17:31
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("file_info")
public class FileInfo extends Model<FileInfo> {
    @TableId
    private Long id;

    private String fileName;

    private String fileUrl;

    private Long fileSize;

    private String fileMd5;

    private Long configId;

    private Long createUser;

    private Integer status;

    private LocalDateTime createTime;
}
