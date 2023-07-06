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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMsgResp implements Serializable {

    private Long id;
    private Long userId;
    private Long friendUserId;

    private String content;
    private Long replyMsgId;
    private LocalDateTime createTime;
}
