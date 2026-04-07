package com.yjc.snaphive.controller;

import com.yjc.snaphive.common.BaseResponse;
import com.yjc.snaphive.common.ResultUtils;
import com.yjc.snaphive.exception.ErrorCode;
import com.yjc.snaphive.exception.ThrowUtils;
import com.yjc.snaphive.model.entity.User;
import com.yjc.snaphive.model.vo.MessageCenterVO;
import com.yjc.snaphive.service.CommentsService;
import com.yjc.snaphive.service.LikeRecordService;
import com.yjc.snaphive.service.ShareRecordService;
import com.yjc.snaphive.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/message")
@Slf4j
public class MessageCenterController {

    @Resource
    private UserService userService;

    @Resource
    private CommentsService commentsService;

    @Resource
    private LikeRecordService likeRecordService;

    @Resource
    private ShareRecordService shareRecordService;

    /**
     * 获取消息中心未读数据
     */
    @GetMapping("/unread/count")
    public BaseResponse<MessageCenterVO> getUnreadCount(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        MessageCenterVO messageCenterVO = new MessageCenterVO();

        // 获取各类型未读数
        long unreadComments = commentsService.getUnreadCommentsCount(loginUser.getId());
        long unreadLikes = likeRecordService.getUnreadLikesCount(loginUser.getId());
        long unreadShares = shareRecordService.getUnreadSharesCount(loginUser.getId());

        // 设置数据
        messageCenterVO.setUnreadComments(unreadComments);
        messageCenterVO.setUnreadLikes(unreadLikes);
        messageCenterVO.setUnreadShares(unreadShares);
        messageCenterVO.setTotalUnread(unreadComments + unreadLikes + unreadShares);

        return ResultUtils.success(messageCenterVO);
    }

    /**
     * 将所有消息标记为已读
     */
    @PostMapping("/read/all")
    public BaseResponse<Boolean> markAllAsRead(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        try {
            // 清除所有类型的未读状�?
            commentsService.clearAllUnreadComments(loginUser.getId());
            likeRecordService.clearAllUnreadLikes(loginUser.getId());
            shareRecordService.clearAllUnreadShares(loginUser.getId());

            return ResultUtils.success(true);
        } catch (Exception e) {
            log.error("Error in markAllAsRead: ", e);
            return ResultUtils.success(false);
        }
    }
}
