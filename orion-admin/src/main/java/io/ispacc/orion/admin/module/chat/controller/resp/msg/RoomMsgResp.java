package io.ispacc.orion.admin.module.chat.controller.resp.msg;

import lombok.Data;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-07-05 17:12
 */
@Data
public class RoomMsgResp extends MessageResp {
    private Long roomId;
}
