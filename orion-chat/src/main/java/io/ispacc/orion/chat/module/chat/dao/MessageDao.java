package io.ispacc.orion.chat.module.chat.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.ispacc.orion.chat.module.chat.entity.Message;
import io.ispacc.orion.chat.module.chat.mapper.MessageMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Message> getPageMsgByRoomId(Long id, Long roomId) {
        return getPageMsgByFriendId(id, roomId, Message::getRoomId);
    }

    public List<Message> getPageMsgByFriendId(Long id, Long friendId) {
        return getPageMsgByFriendId(id, friendId, Message::getFriendUserId);
    }

    private List<Message> getPageMsgByFriendId(Long id, Long columnId, SFunction<Message, ?> leColumnId) {
        IPage<Message> page = new Page<>(1, 20);
        LambdaQueryChainWrapper<Message> lambdaQuery = lambdaQuery();
        lambdaQuery.eq(leColumnId, columnId);
        if (id != null) {
            lambdaQuery.le(Message::getId, id);
        }
        IPage<Message> pages = lambdaQuery.orderByDesc(Message::getId).page(page);
        List<Message> messages = pages.getRecords();
        return messages.stream().sorted(Comparator.comparingLong(Message::getId)).collect(Collectors.toList());
    }
}
