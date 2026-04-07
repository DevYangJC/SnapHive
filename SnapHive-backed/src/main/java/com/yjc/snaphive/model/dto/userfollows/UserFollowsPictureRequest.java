package com.yjc.snaphive.model.dto.userfollows;


import com.yjc.snaphive.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 图片查询请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserFollowsPictureRequest extends PageRequest implements Serializable {
    /**
     * 是否只查�?spaceId �?null 的数�?
     */
    private boolean nullSpaceId;
    /**
     * 用户 id
     */
    private Long userId;

    private static final long serialVersionUID = 1L;
}
