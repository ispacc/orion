package io.ispacc.orion.admin.module.file.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.crypto.digest.DigestUtil;
import io.ispacc.orion.admin.core.constant.FileConstant;
import io.ispacc.orion.admin.core.exception.OrionException;
import io.ispacc.orion.admin.core.utils.UserHolder;
import io.ispacc.orion.admin.module.file.controller.resp.FileUpdateInfoResp;
import io.ispacc.orion.admin.module.file.dao.FileConfigDao;
import io.ispacc.orion.admin.module.file.dao.FileInfoDao;
import io.ispacc.orion.admin.module.file.dao.FileUploadDao;
import io.ispacc.orion.admin.module.file.dao.FileUploadInfoDao;
import io.ispacc.orion.admin.module.file.entity.FileConfig;
import io.ispacc.orion.admin.module.file.entity.FileInfo;
import io.ispacc.orion.admin.module.file.entity.FileUpload;
import io.ispacc.orion.admin.module.file.entity.FileUploadInfo;
import io.ispacc.orion.admin.module.file.prop.FileProperties;
import io.ispacc.orion.admin.module.file.service.adapter.FileUploadInfoAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-07-17 09:33
 */
@Log4j2
@Service
@AllArgsConstructor
public class FileManageServiceImpl implements FileManageService {
    private final FileConfigDao fileConfigDao;
    private final FileInfoDao fileInfoDao;
    private final FileProperties fileProperties;
    private final FileUploadInfoDao fileUploadInfoDao;
    private final FileUploadDao fileUploadDao;

    @Override
    public Long upload(String filePath, MultipartFile file, String fileMd5) {
        FileInfo fileInfo = fileInfoDao.getByMD5(fileMd5);
        if (fileInfo != null) return fileInfo.getId();
        FileConfig fileConfig = fileConfigDao.getById(fileProperties.getConfigId());
        if (!fileConfigDao.checkMaxStore(fileConfig, file.getSize())) throw new OrionException("上传失败,请联系熊宇航");
        String fileStoragePath = getFileStoragePath(fileConfig, filePath, file);
        File storeFile = new File(fileStoragePath);
        try {
            file.transferTo(storeFile);
            String md5 = DigestUtil.md5Hex(storeFile);
            if (!ObjUtil.equal(md5, fileMd5)) throw new OrionException("文件校验失败,请重试！");
        } catch (IOException e) {
            log.error("写文件" + storeFile.getAbsolutePath() + "失败,可能是没有权限或者资源不足！");
            throw new OrionException("上传文件失败,请联系熊宇航！");
        }
        fileInfo = FileInfo.builder()
                .fileSize(storeFile.length())
                .fileUrl(storeFile.getAbsolutePath())
                .fileName(file.getName())
                .configId(fileProperties.getConfigId())
                .fileMd5(fileMd5)
                .createUser(UserHolder.getUserId())
                .status(FileConstant.FileInfoStatus.NORMAL.getStatus())
                .build();
        fileInfoDao.save(fileInfo);
        fileConfigDao.updateUseSize(fileConfig, storeFile.length());
        return fileInfo.getId();
    }

    @Override
    public List<FileUpdateInfoResp> getUpdateFileInfo() {
        List<FileUploadInfo> fileUploadInfos = fileUploadInfoDao.getByIdUserId(UserHolder.getUserId());
        List<Long> fileUploadInfoIds = fileUploadInfos.stream().map(FileUploadInfo::getId).toList();
        if (fileUploadInfoIds.size() == 0) return FileUploadInfoAdapter.getResp(fileUploadInfos, new ArrayList<>());
        List<FileUpload> fileUpload = fileUploadDao.listByIds(fileUploadInfoIds);
        return FileUploadInfoAdapter.getResp(fileUploadInfos, fileUpload);
    }

    @Override
    public void upload(String fileInfoId, MultipartFile chunkFile, String chunkFileMd5, int chunk) {
        return;
    }


    private String getFileStoragePath(FileConfig fileConfig, String filePath, MultipartFile file) {
        String resultPath;
        if (StringUtils.isNotBlank(filePath)) {
            resultPath = fileConfig.getFileRootPath() + filePath;
        } else {
            resultPath = fileConfig.getFileRootPath();
        }
        File pathFile = new File(resultPath);
        if (pathFile.exists() || pathFile.mkdirs()) return resultPath + IdUtil.simpleUUID() + file.getName();
        log.error("创建文件夹" + resultPath + "失败,可能是没有权限或者资源不足！");
        throw new OrionException("上传文件失败,请联系熊宇航！");
    }
}
