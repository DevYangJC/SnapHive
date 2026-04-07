package com.yjc.snaphive.model.dto.share;

import com.yjc.snaphive.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
public class ShareQueryRequest extends PageRequest implements Serializable {
    /**
     * 目标类型：1-图片 2-帖子 3-空间
     */
    private Integer targetType;


    private static final long serialVersionUID = 1L;
}
