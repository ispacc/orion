package io.ispacc.orion.admin.core.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-07-05 16:58
 */
public class ChatConstant {
    @AllArgsConstructor
    @Getter
    public enum MsgType {
        SEND_ROOM_MSG(1, "发送群组消息"),
        SEND_USER_MSG(2, "发送用户消息"),
        SEND_TOPIC_MSG(3, "发送广播通知"),
        ROOM_MSG_ERROR(4, "聊天群组错误消息");

        private final Integer type;
        private final String typeName;
    }
}
