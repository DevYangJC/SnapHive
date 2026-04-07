package com.yjc.snaphive.service;

import com.yjc.snaphive.model.dto.snake.GameRankingRequest;
import com.yjc.snaphive.model.dto.snake.SaveGameRecordRequest;
import com.yjc.snaphive.model.entity.SnakeGameRecord;
import com.yjc.snaphive.model.entity.User;
import com.yjc.snaphive.model.vo.UserHighestScoreVO;

import java.util.List;

public interface SnakeGameService {

    /**
     * 保存游戏记录
     * @param request 游戏记录请求
     * @param loginUser 当前登录用户
     * @return 保存的记�?
     */
    SnakeGameRecord saveGameRecord(SaveGameRecordRequest request, User loginUser);

    /**
     * 获取用户所有模式最高分
     * @param userId 用户ID
     * @return 用户所有模式最高分
     */
    UserHighestScoreVO getUserAllHighestScores(Long userId);

    /**
     * 获取排行�?
     * @param request 排行榜请�?
     * @return 排行榜记录列�?
     */
    List<SnakeGameRecord> getRankingList(GameRankingRequest request);
}
