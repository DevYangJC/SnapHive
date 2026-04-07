package com.yjc.snaphive.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("chat_message")
public class ChatMessage implements Serializable {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    private Long senderId;

    private Long receiverId;

    private Long pictureId;

    private String content;

    private Integer type;

    private Integer status;

    /**
     * 回复的消息id
     */
    private Long replyId;

    /**
     * 会话根消息id
     */
    private Long rootId;

    private Date createTime;

    private Date updateTime;

    @TableLogic
    private Integer isDelete;

    /**
     * 回复的消息内容（非数据库字段）
     */
    @TableField(exist = false)
    private ChatMessage replyMessage;

    /**
     * 发送者信息（非数据库字段）
     */
    @TableField(exist = false)
    private User sender;

    /**
     * 空间id
     */
    private Long spaceId;

    /**
     * 私聊ID
     */
    private Long privateChatId;

    private static final long serialVersionUID = 1L;
}
