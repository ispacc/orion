package io.ispacc.orion.admin.module.chat.controller;

import io.ispacc.orion.admin.common.CommonResult;
import io.ispacc.orion.admin.module.chat.controller.resp.RoomResp;
import io.ispacc.orion.admin.module.chat.controller.resp.UserResp;
import io.ispacc.orion.admin.module.chat.service.ChatService;
import io.ispacc.orion.admin.utils.LoginUserRequestHolder;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Wang Chao
 * @version V1.0
 * @date 2023-06-13 15:07
 */
@AllArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    /**
     * todo 方法级别权限注释待补充
     *
     * @return 获取当前用户加入的群组信息, 默认线程变量一定有值, 交给拦截器做权限校验
     */
    @GetMapping("/user/room")
    public CommonResult<List<RoomResp>> getRoomByCurrentUserId() {
        return CommonResult.success(chatService.getRoomsByUserId(LoginUserRequestHolder.get().getUserId()));
    }

    /**
     * 根据群聊id获取所有用户,并标注用户是否在线
     *
     * @param roomId 群聊id
     * @return 用户信息
     */
    @GetMapping("/room/{roomId}")
    public CommonResult<List<UserResp>> getUserByRoomId(@PathVariable Long roomId) {
        return CommonResult.success(chatService.getUsersByRoomId(roomId));
    }
}
