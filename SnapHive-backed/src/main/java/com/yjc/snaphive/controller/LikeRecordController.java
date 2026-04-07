package com.yjc.snaphive.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjc.snaphive.common.BaseResponse;
import com.yjc.snaphive.common.ResultUtils;
import com.yjc.snaphive.exception.ErrorCode;
import com.yjc.snaphive.exception.ThrowUtils;
import com.yjc.snaphive.model.dto.like.LikeRequest;
import com.yjc.snaphive.model.dto.like.LikeQueryRequest;
import com.yjc.snaphive.model.entity.User;
import com.yjc.snaphive.model.vo.LikeRecordVO;
import com.yjc.snaphive.service.LikeRecordService;
import com.yjc.snaphive.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/like")
@Slf4j
public class LikeRecordController {

    @Resource
    private LikeRecordService likeRecordService;

    @Resource
    private UserService userService;

    /**
     * 通用点赞接口
     */
    @PostMapping("/do")
    public BaseResponse<Boolean> doLike(@RequestBody LikeRequest likeRequest, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        try {
            CompletableFuture<Boolean> future = likeRecordService.doLike(likeRequest, loginUser.getId());
            return ResultUtils.success(true);
        } catch (Exception e) {
            log.error("Error in doLike controller: ", e);
            return ResultUtils.success(false);
        }
    }

    /**
     * 获取点赞状�?
     */
    @GetMapping("/status/{targetType}/{targetId}")
    public BaseResponse<Boolean> getLikeStatus(
            @PathVariable("targetType") Integer targetType,
            @PathVariable("targetId") Long targetId,
            HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        boolean isLiked = likeRecordService.isContentLiked(targetId, targetType, loginUser.getId());
        return ResultUtils.success(isLiked);
    }

    /**
     * 获取未读点赞消息
     */
    @GetMapping("/unread")
    public BaseResponse<List<LikeRecordVO>> getUnreadLikes(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        List<LikeRecordVO> unreadLikes = likeRecordService.getAndClearUnreadLikes(loginUser.getId());
        return ResultUtils.success(unreadLikes);
    }

    /**
     * 获取用户被点赞历�?
     */
    @PostMapping("/history")
    public BaseResponse<Page<LikeRecordVO>> getLikeHistory(@RequestBody LikeQueryRequest likeQueryRequest,
                                                           HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        // 限制爬虫
        long size = likeQueryRequest.getPageSize();
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);

        Page<LikeRecordVO> likeHistory = likeRecordService.getUserLikeHistory(likeQueryRequest, loginUser.getId());
        return ResultUtils.success(likeHistory);
    }

    /**
     * 获取我的点赞历史
     */
    @PostMapping("/my/history")
    public BaseResponse<Page<LikeRecordVO>> getMyLikeHistory(@RequestBody LikeQueryRequest likeQueryRequest,
                                                             HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        // 限制爬虫
        long size = likeQueryRequest.getPageSize();
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);

        Page<LikeRecordVO> likeHistory = likeRecordService.getMyLikeHistory(likeQueryRequest, loginUser.getId());
        return ResultUtils.success(likeHistory);
    }

    @GetMapping("/unread/count")
    public BaseResponse<Long> getUnreadLikesCount(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);
        return ResultUtils.success(likeRecordService.getUnreadLikesCount(loginUser.getId()));
    }
}
