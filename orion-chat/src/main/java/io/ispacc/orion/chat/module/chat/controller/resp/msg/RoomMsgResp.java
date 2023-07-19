package io.ispacc.orion.chat.module.chat.controller.resp.msg;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-07-05 17:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class RoomMsgResp extends MessageResp {
    private Long roomId;
}
