package io.ispacc.orion.chat.module.chat.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-21 17:05
 */
@Getter
public class UserMessageSendEvent extends ApplicationEvent {
    private final Long id;

    public UserMessageSendEvent(Object source, Long msgId) {
        super(source);
        this.id = msgId;
    }
}
