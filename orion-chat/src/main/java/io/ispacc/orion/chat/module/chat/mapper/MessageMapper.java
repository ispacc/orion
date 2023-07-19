package io.ispacc.orion.chat.module.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.ispacc.orion.chat.module.chat.entity.Message;
import org.springframework.stereotype.Repository;

/**
 * 群聊消息表
 *
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-21 16:12
 */
@Repository
public interface MessageMapper extends BaseMapper<Message> {
}
