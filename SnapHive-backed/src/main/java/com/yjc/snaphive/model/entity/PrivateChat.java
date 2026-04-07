package com.yjc.snaphive.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.yjc.snaphive.model.vo.UserVO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@TableName(value = "private_chat")
@Data
public class PrivateChat implements Serializable {

    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 目标用户id
     */
    private Long targetUserId;

    /**
     * 最后一条消息内�?
     */
    private String lastMessage;

    /**
     * 最后一条消息时�?
     */
    private Date lastMessageTime;

    /**
     * 用户未读消息�?
     */
    private Integer userUnreadCount;

    /**
     * 目标用户未读消息�?
     */
    private Integer targetUserUnreadCount;

    /**
     * 聊天类型�?-私信 1-好友(双向关注)
     */
    private Integer chatType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    /**
     * 目标用户信息（脱敏）
     */
    @TableField(exist = false)
    private UserVO targetUser;

    /**
     * 当前用户是否为发送者（true表示当前用户是userId，false表示当前用户是targetUserId�?
     */
    @TableField(exist = false)
    private Boolean isSender;

    /**
     * 用户自定义的私聊名称
     */
    private String userChatName;

    /**
     * 目标用户自定义的私聊名称
     */
    private String targetUserChatName;

    private static final long serialVersionUID = 1L;
}
