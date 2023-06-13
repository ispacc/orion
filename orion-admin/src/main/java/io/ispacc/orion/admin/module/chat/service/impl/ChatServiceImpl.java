package io.ispacc.orion.admin.module.chat.service.impl;

import io.ispacc.orion.admin.constant.RedisConstant;
import io.ispacc.orion.admin.module.admin.dao.UserDao;
import io.ispacc.orion.admin.module.admin.entity.User;
import io.ispacc.orion.admin.module.chat.controller.resp.RoomResp;
import io.ispacc.orion.admin.module.chat.controller.resp.UserResp;
import io.ispacc.orion.admin.module.chat.dao.RoomDao;
import io.ispacc.orion.admin.module.chat.entity.Room;
import io.ispacc.orion.admin.module.chat.service.ChatService;
import io.ispacc.orion.admin.module.chat.service.adapter.RoomAdapter;
import io.ispacc.orion.admin.module.chat.service.adapter.UserAdapter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 消息处理类
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 15:28
 */
@AllArgsConstructor
@Service
@Slf4j
public class ChatServiceImpl implements ChatService {
    private final RoomDao roomDao;
    private final UserDao userDao;
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public List<RoomResp> getRoomsByUserId(Long userId) {
        List<Room> roomEntityList = roomDao.getByUserId(userId);
        return RoomAdapter.buildResp(roomEntityList);
    }

    @Override
    public List<UserResp> getUsersByRoomId(Long roomId) {
        List<User> userEntityList = userDao.getByRoomId(roomId);
        Set<Long> onlineUserIds = userEntityList.stream().map(User::getUserId).collect(Collectors.toSet());
        return UserAdapter.buildResp(userEntityList, onlineUserIds);
    }

    //todo 后续根据熊宇航的redis module编写情况 可迁移至redis模块
    //todo 空指针原因暂时未知 查询资料 判断是否要修改代码
    //将在线的当前用户存储到返回值map中
    //1在线 0离线
    private Set<Long> getUsersInOnline(Set<Long> userId) {
        if (userId.size() < 1) return new HashSet<>();
        //在线人数
        Long onlineSize = redisTemplate.opsForSet().size(RedisConstant.websocket_online_users);
        //如果为null,按照无人在线处理
        if (onlineSize == null) return new HashSet<>();
        //如果在线人数过多的话,根据selectSize循环判断,否则获取所有onlineSize循环判断,节约内存
        if (onlineSize < 500) {
            Set<String> onlineUserIds = redisTemplate.opsForSet().members(RedisConstant.websocket_online_users);
            if (onlineUserIds == null) return new HashSet<>();
            return onlineUserIds.stream().map(Long::parseLong).filter(userId::contains).collect(Collectors.toSet());
        } else {
            return userId.stream().filter(o -> redisTemplate.opsForSet().isMember(RedisConstant.websocket_online_users, o)).collect(Collectors.toSet());
        }
    }

}
