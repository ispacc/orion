package io.ispacc.orion.admin.module.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-12 12:36
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage implements Serializable {
    private MessageType type;

    private String content;

    private String sender;
    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE
    }
}
