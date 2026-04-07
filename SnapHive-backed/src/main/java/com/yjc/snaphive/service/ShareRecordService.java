package com.yjc.snaphive.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.snaphive.model.dto.share.ShareRequest;
import com.yjc.snaphive.model.entity.ShareRecord;
import com.yjc.snaphive.model.dto.share.ShareQueryRequest;
import com.yjc.snaphive.model.vo.ShareRecordVO;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ShareRecordService extends IService<ShareRecord> {
    /**
     * 通用分享/取消分享
     */
    CompletableFuture<Boolean> doShare(ShareRequest shareRequest, Long userId);

    /**
     * 获取并清除用户未读的分享消息
     */
    List<ShareRecordVO> getAndClearUnreadShares(Long userId);

    /**
     * 获取用户的分享历�?
     */
    Page<ShareRecordVO> getUserShareHistory(ShareQueryRequest shareQueryRequest, Long userId);

    /**
     * 检查内容是否已被用户分�?
     */
    boolean isContentShared(Long targetId, Integer targetType, Long userId);

    /**
     * 获取用户未读分享�?
     */
    long getUnreadSharesCount(Long userId);

    /**
     * 清除用户所有未读分享状�?
     */
    void clearAllUnreadShares(Long userId);

    /**
     * 获取用户自己的分享历史（分页�?
     */
    Page<ShareRecordVO> getMyShareHistory(ShareQueryRequest shareQueryRequest, Long userId);

    /**
     * 分享内容
     */
    void shareContent(Long targetId, Integer targetType, Long userId);

    /**
     * 取消分享内容
     */
    void unshareContent(Long targetId, Integer targetType, Long userId);
}
