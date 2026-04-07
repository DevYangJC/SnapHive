package com.yjc.snaphive.model.vo;

import lombok.Data;

@Data
public class MessageCenterVO {
    /**
     * 未读消息总数
     */
    private long totalUnread;

    /**
     * 未读评论�?
     */
    private long unreadComments;

    /**
     * 未读点赞�?
     */
    private long unreadLikes;

    /**
     * 未读分享�?
     */
    private long unreadShares;
}
