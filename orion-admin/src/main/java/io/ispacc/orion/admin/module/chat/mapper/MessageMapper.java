package io.ispacc.orion.admin.module.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.ispacc.orion.admin.module.chat.entity.Message;
import org.apache.ibatis.annotations.Mapper;

/**
 * 群聊消息表
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-21 16:12
 */
@Mapper
public interface MessageMapper extends BaseMapper<Message> {
}
