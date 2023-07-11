package io.ispacc.orion.admin.module.chat.controller;

import io.ispacc.orion.admin.core.common.CommonResult;
import io.ispacc.orion.admin.core.utils.UserHolder;
import io.ispacc.orion.admin.module.chat.controller.req.RoomMessageReq;
import io.ispacc.orion.admin.module.chat.controller.req.UserMessageReq;
import io.ispacc.orion.admin.module.chat.controller.resp.RoomResp;
import io.ispacc.orion.admin.module.chat.controller.resp.UserFriendResp;
import io.ispacc.orion.admin.module.chat.controller.resp.msg.RoomMsgResp;
import io.ispacc.orion.admin.module.chat.controller.resp.msg.UserMsgResp;
import io.ispacc.orion.admin.module.chat.controller.resp.user.RoomUserResp;
import io.ispacc.orion.admin.module.chat.service.ChatService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
        return CommonResult.success(chatService.getRoomsByUserId(UserHolder.getUserId()));
    }

    /**
     * 根据群聊id获取所有用户,并标注用户是否在线
     *
     * @param roomId 群聊id
     * @return 用户信息
     */
    @GetMapping("/room/{roomId}")
    public CommonResult<List<RoomUserResp>> getUserByRoomId(@PathVariable Long roomId) {
        return CommonResult.success(chatService.getUsersByRoomId(roomId));
    }

    /**
     * @return 获取当前用户好友信息, 默认线程变量一定有值, 交给拦截器做权限校验,并标注用户是否在线
     */
    @GetMapping("/user/friend")
    public CommonResult<List<UserFriendResp>> getUserFriendByCurrentUserId() {
        return CommonResult.success(chatService.getFriendsByUserId(UserHolder.getUserId()));
    }

    /**
     * 获取群聊消息列表
     *
     * @param msgId  用户的最后一条消息,如果有则往前取,没有则取最新的
     * @param roomId 聊天室id
     * @return 消息列表
     */
    @GetMapping("/msg/room")
    public CommonResult<List<RoomMsgResp>> getPageMsgByRoomId(@RequestParam Long roomId, @RequestParam(required = false) Long msgId) {
        return CommonResult.success(chatService.getPageMsgByRoomId(roomId, msgId));
    }

    /**
     * 获取用户消息列表
     *
     * @param friendId 好友id
     * @param msgId    用户的最后一条消息,如果有则往前取,没有则取最新的
     * @return 消息列表
     */
    @GetMapping("/msg/user")
    public CommonResult<List<UserMsgResp>> getPageMsgByFriendId(@RequestParam Long friendId, @RequestParam(required = false) Long msgId) {
        return CommonResult.success(chatService.getPageMsgByFriendId(friendId, msgId));
    }

    /**
     * 发送消息至群聊
     *
     * @param message 消息信息
     * @return 消息id
     */
    @PostMapping("/msg/room")
    public CommonResult<?> send(@Valid @RequestBody RoomMessageReq message) {
        Long msgId = chatService.sendMsgToRoomId(message, UserHolder.getUserId());
        return CommonResult.success("msgId", msgId);
    }

    /**
     * 发送消息至好友
     *
     * @param message 消息信息
     * @return 消息id
     */
    @PostMapping("/msg/user")
    public CommonResult<?> send(@Valid @RequestBody UserMessageReq message) {
        Long msgId = chatService.sendMsgToUserId(message, UserHolder.getUserId());
        return CommonResult.success("msgId", msgId);
    }

    /**
     * 将用户移除群聊
     */
    @PostMapping("/room/remove/{roomId}/{userId}")
    public CommonResult<?> removeUserByRoomId(@PathVariable Long roomId, @PathVariable Long userId) {
        chatService.removeUserByRoomId(roomId, UserHolder.getUserId(), userId);
        return CommonResult.success();
    }
}
