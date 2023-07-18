package io.ispacc.orion.admin.module.file.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.ispacc.orion.admin.module.file.entity.FileUploadInfo;
import io.ispacc.orion.admin.module.file.mapper.FileUploadInfoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 文件上传分块 总
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 15:18
 */
@AllArgsConstructor
@Service
public class FileUploadInfoDao extends ServiceImpl<FileUploadInfoMapper, FileUploadInfo> {
    private final FileUploadInfoMapper fileUploadInfoMapper;

    public List<FileUploadInfo> getByIdUserId(Long userId) {
        return fileUploadInfoMapper.getByIdUserId(userId);
    }
}
