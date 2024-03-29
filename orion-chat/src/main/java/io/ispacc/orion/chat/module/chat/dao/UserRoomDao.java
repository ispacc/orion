package io.ispacc.orion.chat.module.chat.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.ispacc.orion.chat.module.chat.entity.UserRoom;
import io.ispacc.orion.chat.module.chat.mapper.UserRoomMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 群聊表
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 15:18
 */
@AllArgsConstructor
@Service
public class UserRoomDao extends ServiceImpl<UserRoomMapper, UserRoom> {

    private final UserRoomMapper userRoomMapper;

    public List<Long> getUserIdByRoomId(Long roomId) {
        List<UserRoom> userRooms = lambdaQuery().eq(UserRoom::getRoomId, roomId).select(UserRoom::getUserId).list();
        return userRooms.stream().map(UserRoom::getUserId).collect(Collectors.toList());
    }

    public boolean existsUserInRoom(Long userId, Long roomId) {
        return lambdaQuery().eq(UserRoom::getRoomId, roomId).eq(UserRoom::getUserId, userId).count() > 0;
    }

    public void removeUser(Long userId, Long roomId) {
        lambdaUpdate().eq(UserRoom::getUserId, userId).eq(UserRoom::getRoomId, roomId).remove();
    }
}
