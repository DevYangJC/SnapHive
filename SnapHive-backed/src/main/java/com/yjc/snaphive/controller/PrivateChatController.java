package com.yjc.snaphive.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjc.snaphive.common.BaseResponse;
import com.yjc.snaphive.common.ResultUtils;
import com.yjc.snaphive.exception.BusinessException;
import com.yjc.snaphive.exception.ErrorCode;
import com.yjc.snaphive.exception.ThrowUtils;
import com.yjc.snaphive.model.dto.privatechat.PrivateChatQueryRequest;
import com.yjc.snaphive.model.entity.PrivateChat;
import com.yjc.snaphive.model.entity.User;
import com.yjc.snaphive.service.PrivateChatService;
import com.yjc.snaphive.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/private_chat")
@Slf4j
public class PrivateChatController {

    @Resource
    private PrivateChatService privateChatService;

    @Resource
    private UserService userService;

    /**
     * 分页获取私聊列表
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<PrivateChat>> listPrivateChatByPage(@RequestBody PrivateChatQueryRequest privateChatQueryRequest,
                                                                 HttpServletRequest request) {
        if (privateChatQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        long current = privateChatQueryRequest.getCurrent();
        long size = privateChatQueryRequest.getPageSize();

        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);

        Page<PrivateChat> privateChatPage = privateChatService.page(new Page<>(current, size),
                privateChatService.getQueryWrapper(privateChatQueryRequest, loginUser), request);
        return ResultUtils.success(privateChatPage);
    }

    /**
     * 清空未读消息�?
     */
    @PostMapping("/clear_unread/{targetUserId}/{isSender}")
    public BaseResponse<Boolean> clearUnreadCount(@PathVariable Long targetUserId,boolean isSender,
                                                  HttpServletRequest request) {
        ThrowUtils.throwIf(targetUserId == null || targetUserId <= 0, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);
        // 清除当前用户的未读消息数
        privateChatService.clearUnreadCount(loginUser.getId(), targetUserId, isSender);
        return ResultUtils.success(true);
    }

    /**
     * 更新聊天类型（好�?私信�?
     */
    @PostMapping("/update_type/{targetUserId}")
    public BaseResponse<Boolean> updateChatType(@PathVariable Long targetUserId,
                                                @RequestParam Boolean isFriend,
                                                HttpServletRequest request) {
        ThrowUtils.throwIf(targetUserId == null || targetUserId <= 0, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);
        privateChatService.updateChatType(loginUser.getId(), targetUserId, isFriend);
        return ResultUtils.success(true);
    }

    /**
     * 创建或更新私�?
     */
    @PostMapping("/create_update")
    public BaseResponse<PrivateChat> createOrUpdatePrivateChat(@RequestParam Long targetUserId,
                                                               @RequestParam(required = false) String lastMessage,
                                                               HttpServletRequest request) {
        ThrowUtils.throwIf(targetUserId == null || targetUserId <= 0, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);

        // 不能和自己私�?
        ThrowUtils.throwIf(targetUserId.equals(loginUser.getId()), ErrorCode.PARAMS_ERROR, "不能和自己私�?);

        // 检查目标用户是否存�?
        User targetUser = userService.getById(targetUserId);
        ThrowUtils.throwIf(targetUser == null, ErrorCode.NOT_FOUND_ERROR, "目标用户不存�?);

        PrivateChat privateChat = privateChatService.createOrUpdatePrivateChat(loginUser.getId(), targetUserId, lastMessage);
        return ResultUtils.success(privateChat);
    }

    /**
     * 删除私聊
     */
    @PostMapping("/delete/{privateChatId}")
    public BaseResponse<Boolean> deletePrivateChat(@PathVariable Long privateChatId,
                                                   HttpServletRequest request) {
        ThrowUtils.throwIf(privateChatId == null || privateChatId <= 0, ErrorCode.PARAMS_ERROR);
        User loginUser = userService.getLoginUser(request);
        boolean result = privateChatService.deletePrivateChat(privateChatId, loginUser);
        return ResultUtils.success(result);
    }

    /**
     * 修改私聊名称
     */
    @PostMapping("/update_name/{privateChatId}")
    public BaseResponse<Boolean> updateChatName(@PathVariable Long privateChatId,
                                                @RequestParam String chatName,
                                                HttpServletRequest request) {
        ThrowUtils.throwIf(privateChatId == null || privateChatId <= 0, ErrorCode.PARAMS_ERROR);
        ThrowUtils.throwIf(chatName.length() > 50, ErrorCode.PARAMS_ERROR, "聊天名称过长");

        User loginUser = userService.getLoginUser(request);
        privateChatService.updateChatName(privateChatId, chatName, loginUser);
        return ResultUtils.success(true);
    }
}
