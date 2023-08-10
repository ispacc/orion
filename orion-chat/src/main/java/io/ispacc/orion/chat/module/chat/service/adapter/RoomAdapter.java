package io.ispacc.orion.chat.module.chat.service.adapter;

import cn.hutool.core.bean.BeanUtil;
import io.ispacc.orion.chat.module.chat.controller.resp.RoomResp;
import io.ispacc.orion.chat.module.chat.entity.Room;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 15:56
 */
public class RoomAdapter {
    public static List<RoomResp> buildResp(List<Room> list) {
        return list.stream()
                .map(a -> {
                    RoomResp resp = new RoomResp();
                    BeanUtil.copyProperties(a, resp);
                    return resp;
                }).collect(Collectors.toList());
    }
}
