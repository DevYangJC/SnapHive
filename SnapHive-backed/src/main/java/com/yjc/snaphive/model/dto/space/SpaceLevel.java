package com.yjc.snaphive.model.dto.space;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 空间级别
 */
@Data
@AllArgsConstructor
public class SpaceLevel {

    /**
     * �?
     */
    private int value;

    /**
     * 中文
     */
    private String text;

    /**
     * 最大数�?
     */
    private long maxCount;

    /**
     * 最大容�?
     */
    private long maxSize;
}
