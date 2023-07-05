package io.ispacc.orion.admin.module.chat.controller.req;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-07-05 16:17
 */
public class UserMessageReq implements Serializable {
    @NotNull
    private Long userId;
    @NotEmpty
    private String content;
    private Long replyMsgId;
}
