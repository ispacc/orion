package io.ispacc.orion.admin.module.chat.controller.resp.msg;

import lombok.Data;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-07-06 18:34
 */
@Data
public class UserMsgResp extends MessageResp {
    private Long friendUserId;
}
