package com.yjc.snaphive.model.dto.space;

import lombok.Data;

import java.io.Serializable;

/**
 * 创建空间请求
 */
@Data
public class SpaceAddRequest implements Serializable {

    /**
     * 空间名称
     */
    private String spaceName;
    /**
     * 空间类型�?-私有 1-团队
     */
    private Integer spaceType;

    /**
     * 空间级别�?-普通版 1-专业�?2-旗舰�?
     */
    private Integer spaceLevel;

    private static final long serialVersionUID = 1L;
}
