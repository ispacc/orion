package io.ispacc.orion.admin.module.chat.controller.req;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-07-05 20:26
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageReq implements Serializable {
    @NotBlank(message = "消息不能为空")
    @Length(max = 3, message = "输入消息过长")
    private String content;

    private Long replyMsgId;
}
