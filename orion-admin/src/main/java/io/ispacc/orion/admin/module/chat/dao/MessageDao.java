package io.ispacc.orion.admin.module.chat.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.ispacc.orion.admin.module.chat.entity.Message;
import io.ispacc.orion.admin.module.chat.mapper.MessageMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-21 16:12
 */
@AllArgsConstructor
@Service
public class MessageDao extends ServiceImpl<MessageMapper, Message> {
    private final MessageMapper mapper;

    public Message getByIdExistsRoomId(Long id, Long roomId) {
        return this.lambdaQuery().eq(Message::getId, id).eq(Message::getRoomId, roomId).one();
    }
}
