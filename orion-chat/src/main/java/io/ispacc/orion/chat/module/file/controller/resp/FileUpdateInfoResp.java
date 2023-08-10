package io.ispacc.orion.chat.module.file.controller.resp;

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
    List<Integer> alreadyUpdateChunkList = new ArrayList<>();
    private Long id;
    private Integer fileChunkNum;
    private Integer fileChunkSize;
    private String localUrl;
    private LocalDateTime createTime;

    public void addAlreadyUpdateChunk(Integer chunk) {
        alreadyUpdateChunkList.add(chunk);
    }

    public void removeAlreadyUpdateChunk(Integer chunk) {
        alreadyUpdateChunkList.remove(chunk);
    }
}
