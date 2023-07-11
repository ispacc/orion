package io.ispacc.orion.admin.module.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.ispacc.orion.admin.module.chat.entity.Room;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 群聊表
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 15:17
 */
@Repository
public interface RoomMapper extends BaseMapper<Room> {

    @Select("SELECT r.* FROM map_user_chatroom ur LEFT JOIN cms_chatroom r ON ur.room_id = r.id WHERE ur.user_id = #{userId}")
    List<Room> getRoomsByUserId(@Param("userId") Long userId);

    @Select("SELECT tr.* from map_user_chatroom ur left join cms_chatroom tr on ur.room_id = tr.id where tr.id=#{roomId} and ur.user_id=#{userId}")
    Room getRoomByIdExistsUserId(@Param("roomId") Long roomId, @Param("userId") Long userId);
}
