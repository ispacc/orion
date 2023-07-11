package io.ispacc.orion.admin.module.chat.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-07-06 17:53
 */
@Getter
public class RoomMessageRemoveUserEvent extends ApplicationEvent {
    private final Long roomId;
    private final Long manageUserId;
    private final Long removeUserId;

    public RoomMessageRemoveUserEvent(Object source, Long roomId, Long manageUserId, Long removeUserId) {
        super(source);
        this.roomId = roomId;
        this.manageUserId = manageUserId;
        this.removeUserId = removeUserId;
    }
}
