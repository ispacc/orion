package io.ispacc.orion.chat.module.file.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.ispacc.orion.chat.module.file.entity.FileConfig;
import io.ispacc.orion.chat.module.file.mapper.FileConfigMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 文件配置
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 15:18
 */
@AllArgsConstructor
@Service
public class FileConfigDao extends ServiceImpl<FileConfigMapper, FileConfig> {

    public void updateUseSize(FileConfig fileConfig, Long fileSize) {
        fileConfig.setUseSize(fileConfig.getUseSize() + fileSize);
        updateById(fileConfig);
    }

    public boolean checkMaxStore(FileConfig fileConfig, Long fileSize) {
        boolean b = fileConfig.getUseSize() + fileSize <= fileConfig.getMaxSize();
        if (!b) log.error("文件服务id【" + fileConfig.getId() + "】已满,请处理！");
        return b;
    }
}
