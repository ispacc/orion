package io.ispacc.orion.admin.module.chat.controller.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-07-05 17:12
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomMsgResp implements Serializable {

    private Long id;

    private Long roomId;

    private Long userId;

    private String content;
    private Long replyMsgId;
    private LocalDateTime createTime;
}
