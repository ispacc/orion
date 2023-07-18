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
 * 文件配置表
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-15 17:31
 */

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("file_config")
public class FileConfig extends Model<FileConfig> {
    @TableId
    private Long id;

    private String fileRootPath;

    private Long maxSize;

    private Long useSize;

    private LocalDateTime createTime;
}
