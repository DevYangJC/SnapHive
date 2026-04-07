package com.yjc.snaphive.model.dto.snake;

import lombok.Data;

@Data
public class GameRankingRequest {
    private Integer gameMode;
    private Integer limit = 10;
} 
