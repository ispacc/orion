package io.ispacc.orion.chat.module.file.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.ispacc.orion.chat.module.file.entity.FileUpload;
import io.ispacc.orion.chat.module.file.mapper.FileUploadMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 文件上传分块 分
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 15:18
 */
@AllArgsConstructor
@Service
public class FileUploadDao extends ServiceImpl<FileUploadMapper, FileUpload> {
}
