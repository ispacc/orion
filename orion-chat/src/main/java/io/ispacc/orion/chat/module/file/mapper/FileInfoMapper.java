package io.ispacc.orion.chat.module.file.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.ispacc.orion.chat.module.file.entity.FileInfo;
import org.springframework.stereotype.Repository;

/**
 * 文件存储
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 15:17
 */
@Repository
public interface FileInfoMapper extends BaseMapper<FileInfo> {
}
