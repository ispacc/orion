package io.ispacc.orion.admin.module.chat.service.adapter;

import cn.hutool.core.bean.BeanUtil;
import io.ispacc.orion.admin.module.chat.controller.req.RoomMessageReq;
import io.ispacc.orion.admin.module.chat.entity.Message;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-21 16:06
 */
public class MessageAdapter {

    public static Message buildEntity(RoomMessageReq messageReq, Long userId) {
        Message message = new Message();
        BeanUtil.copyProperties(messageReq, message);
        message.setUserId(userId);
        return message;
    }
}
