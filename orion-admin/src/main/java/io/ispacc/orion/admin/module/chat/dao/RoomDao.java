package io.ispacc.orion.admin.module.chat.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.ispacc.orion.admin.module.chat.entity.Room;
import io.ispacc.orion.admin.module.chat.mapper.RoomMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 群聊表
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 15:18
 */
@AllArgsConstructor
@Service
public class RoomDao extends ServiceImpl<RoomMapper, Room> {

    private final RoomMapper roomMapper;

    public List<Room> getByUserId(Long userId) {
        return roomMapper.getRoomsByUserId(userId);
    }
}
