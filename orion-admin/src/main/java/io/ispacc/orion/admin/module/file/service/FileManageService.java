package io.ispacc.orion.admin.module.file.service;

import io.ispacc.orion.admin.module.file.controller.resp.FileUpdateInfoResp;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 文件上传、下载基类
 */
public interface FileManageService {

    /**
     * 小文件上传
     *
     * @param filePath 文件地址
     * @param file     文件
     * @param fileMd5  文件md5 用于校验文件是否已经存储
     */
    public Long upload(String filePath, MultipartFile file, String fileMd5);

    /**
     * @return 获取正在上传的文件
     */
    public List<FileUpdateInfoResp> getUpdateFileInfo();

    /**
     * 大文件分块上传
     *
     * @param fileInfoId   文件id
     * @param chunkFile    当前分块文件
     * @param chunkFileMd5 分块文件md5
     * @param chunk        当前块
     */
    public void upload(String fileInfoId, MultipartFile chunkFile, String chunkFileMd5, int chunk);
}
