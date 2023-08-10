package io.ispacc.orion.chat.core.constant;

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
        ROOM_MSG_ERROR(4, "聊天群组错误消息"),
        ROOM_REMOVE_USER_MSG(5, "将用户移除群组");

        private final Integer type;
        private final String typeName;
    }
}
