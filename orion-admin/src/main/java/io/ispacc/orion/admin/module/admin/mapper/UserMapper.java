package io.ispacc.orion.admin.module.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.ispacc.orion.admin.module.admin.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {

    @Select("select u.* from t_user_room ur left join t_user u on ur.user_id=u.user_id where ur.room_id=#{roomId}")
    List<User> getUsersByRoomId(@Param("roomId") Long roomId);
}
