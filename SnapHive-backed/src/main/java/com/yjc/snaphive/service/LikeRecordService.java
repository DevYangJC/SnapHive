package com.yjc.snaphive.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjc.snaphive.model.dto.like.LikeRequest;
import com.yjc.snaphive.model.entity.LikeRecord;
import com.yjc.snaphive.model.vo.LikeRecordVO;
import com.yjc.snaphive.model.dto.like.LikeQueryRequest;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface LikeRecordService extends IService<LikeRecord> {
    /**
     * 通用点赞/取消点赞
     */
    CompletableFuture<Boolean> doLike(LikeRequest likeRequest, Long userId);

    /**
     * 获取并清除用户未读的点赞消息
     */
    List<LikeRecordVO> getAndClearUnreadLikes(Long userId);

    /**
     * 获取用户的点赞历史（分页�?
     */
    Page<LikeRecordVO> getUserLikeHistory(LikeQueryRequest likeQueryRequest, Long userId);

    /**
     * 检查内容是否已被用户点�?
     */
    boolean isContentLiked(Long targetId, Integer targetType, Long userId);

    /**
     * 获取用户未读点赞�?
     */
    long getUnreadLikesCount(Long userId);

    /**
     * 清除用户所有未读点赞状�?
     */
    void clearAllUnreadLikes(Long userId);

    /**
     * 获取用户自己的点赞历史（分页�?
     */
    Page<LikeRecordVO> getMyLikeHistory(LikeQueryRequest likeQueryRequest, Long userId);

    /**
     * 点赞内容
     */
    void likeContent(Long targetId, Integer targetType, Long userId);

    /**
     * 取消点赞内容
     */
    void unlikeContent(Long targetId, Integer targetType, Long userId);

    long getRealTimeLikeCount(Long pictureId);
}
