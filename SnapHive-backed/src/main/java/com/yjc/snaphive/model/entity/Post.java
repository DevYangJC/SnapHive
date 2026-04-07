package com.yjc.snaphive.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.yjc.snaphive.model.vo.UserVO;
import lombok.Data;


import java.util.List;
/**
 * 论坛帖子�?
 * @TableName post
 */
@Data
@TableName("post")
public class Post implements Serializable {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long userId;

    private String title;

    private String content;

    private String category;

    private String tags;

    private Long viewCount;

    private Long likeCount;

    private Long commentCount;

    private Integer status;

    private String reviewMessage;

    private Date createTime;

    private Date updateTime;

    private Integer isDelete;

    @TableField(exist = false)
    private List<PostAttachment> attachments;

    @TableField(exist = false)
    private UserVO user;

    @TableField(exist = false)
    private Integer isLiked;

    @TableField(exist = false)
    private Integer isShared;

    /**
     * 分享�?
     */
    private Long shareCount;
}
