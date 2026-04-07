package com.yjc.snaphive.model.vo;

import lombok.Data;

import java.util.Date;

@Data
public class LikeRecordVO {
    /**
     * 点赞ID
     */
    private Long id;

    /**
     * 最近点赞时�?
     */
    private Date lastLikeTime;

    /**
     * 点赞用户信息
     */
    private UserVO user;

    /**
     * 内容类型�?-图片 2-帖子 3-空间
     */
    private Integer targetType;

    /**
     * 被点赞内容所属用户ID
     */
    private Long targetUserId;

    /**
     * 被点赞的内容（根据targetType可能是PictureVO/Post/SpaceVO�?
     */
    private Object target;
}
