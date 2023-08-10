package io.ispacc.orion.chat.module.file.service.adapter;

import cn.hutool.core.bean.BeanUtil;
import io.ispacc.orion.chat.module.file.controller.resp.FileUpdateInfoResp;
import io.ispacc.orion.chat.module.file.entity.FileUpload;
import io.ispacc.orion.chat.module.file.entity.FileUploadInfo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-07-18 11:33
 */
public class FileUploadInfoAdapter {
    public static List<FileUpdateInfoResp> getResp(List<FileUploadInfo> fileUpdateInfos, List<FileUpload> fileUploads) {
        List<FileUpdateInfoResp> respList = BeanUtil.copyToList(fileUpdateInfos, FileUpdateInfoResp.class);
        Map<Long, FileUpdateInfoResp> respMap = respList.stream().collect(Collectors.toMap(FileUpdateInfoResp::getId, o -> o));
        for (FileUpload fileUpload : fileUploads) {
            FileUpdateInfoResp fileUpdateInfoResp = respMap.get(fileUpload.getFileUploadInfoId());
            fileUpdateInfoResp.addAlreadyUpdateChunk(fileUpload.getFileChunk());
        }
        return respList;
    }
}
