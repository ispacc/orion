package io.ispacc.orion.chat.module.file.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.ispacc.orion.chat.module.file.entity.FileInfo;
import io.ispacc.orion.chat.module.file.mapper.FileInfoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 文件存储
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 15:18
 */
@AllArgsConstructor
@Service
public class FileInfoDao extends ServiceImpl<FileInfoMapper, FileInfo> {
    public FileInfo getByMD5(String md5) {
        return lambdaQuery().eq(FileInfo::getFileMd5, md5).one();
    }
}
