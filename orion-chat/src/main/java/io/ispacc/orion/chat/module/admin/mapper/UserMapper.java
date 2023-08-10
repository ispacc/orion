package io.ispacc.orion.chat.module.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.ispacc.orion.chat.module.admin.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select("select u.* from map_user_chatroom ur left join ums_user u on ur.user_id=u.user_id where ur.room_id=#{roomId}")
    List<User> getUsersByRoomId(@Param("roomId") Long roomId);
}
