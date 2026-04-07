package com.yjc.snaphive.model.dto.game2048;

import lombok.Data;

import java.io.Serializable;

/**
 * 保存游戏记录请求
 */
@Data
public class SaveGameRecordRequest implements Serializable {

    /**
     * 得分
     */
    private Integer score;

    /**
     * 最大数�?
     */
    private Integer maxTile;

    /**
     * 游戏时长(�?
     */
    private Integer gameTime;

    /**
     * 移动次数
     */
    private Integer moveCount;

    private static final long serialVersionUID = 1L;
} 
