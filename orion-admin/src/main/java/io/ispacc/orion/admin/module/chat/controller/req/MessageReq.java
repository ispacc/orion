package io.ispacc.orion.admin.module.chat.controller.req;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-07-05 20:26
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class MessageReq implements Serializable {
    @NotEmpty
    private String content;

    private Long replyMsgId;
}
