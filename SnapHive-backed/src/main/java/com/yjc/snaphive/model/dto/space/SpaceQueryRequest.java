package com.yjc.snaphive.model.dto.space;

import com.yjc.snaphive.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 查询空间请求
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SpaceQueryRequest extends PageRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 空间名称
     */
    private String spaceName;

    /**
     * 空间级别�?-普通版 1-专业�?2-旗舰�?
     */
    private Integer spaceLevel;

    /**
     * 空间类型�?-私有 1-团队
     */
    private Integer spaceType;

    private static final long serialVersionUID = 1L;
}
