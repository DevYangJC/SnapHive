package com.yjc.snaphive.model.dto.space;

import lombok.Data;

import java.io.Serializable;

/**
 * 更新空间请求
 */
@Data
public class SpaceUpdateRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 空间名称
     */
    private String spaceName;

    /**
     * 空间级别�?-普通版 1-专业�?2-旗舰�?
     */
    private Integer spaceLevel;

    /**
     * 空间图片的最大总大�?
     */
    private Long maxSize;

    /**
     * 空间图片的最大数�?
     */
    private Long maxCount;

    private static final long serialVersionUID = 1L;
}
