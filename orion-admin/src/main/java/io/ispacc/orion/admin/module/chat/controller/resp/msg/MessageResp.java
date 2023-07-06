package io.ispacc.orion.admin.module.chat.controller.resp.msg;

import io.ispacc.orion.admin.module.chat.controller.resp.user.BaseUserResp;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-07-06 18:33
 */
@Data
public class MessageResp {
    private Long id;
    private Long userId;
    private BaseUserResp userInfo;
    private String content;
    private Long replyMsgId;
    private LocalDateTime createTime;
}
