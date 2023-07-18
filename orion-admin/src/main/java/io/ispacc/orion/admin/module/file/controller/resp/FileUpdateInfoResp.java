package io.ispacc.orion.admin.module.file.controller.resp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-07-18 11:05
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileUpdateInfoResp {
    private Long id;
    private Integer fileChunkNum;
    private Integer fileChunkSize;
    private String localUrl;
    private LocalDateTime createTime;

    List<Integer> alreadyUpdateChunkList = new ArrayList<>();

    public void addAlreadyUpdateChunk(Integer chunk) {
        alreadyUpdateChunkList.add(chunk);
    }

    public void removeAlreadyUpdateChunk(Integer chunk) {
        alreadyUpdateChunkList.remove(chunk);
    }
}
