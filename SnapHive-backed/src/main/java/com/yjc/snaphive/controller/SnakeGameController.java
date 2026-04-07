package com.yjc.snaphive.controller;

import com.yjc.snaphive.common.BaseResponse;
import com.yjc.snaphive.common.ResultUtils;
import com.yjc.snaphive.model.dto.snake.GameRankingRequest;
import com.yjc.snaphive.model.dto.snake.SaveGameRecordRequest;
import com.yjc.snaphive.model.entity.SnakeGameRecord;
import com.yjc.snaphive.model.entity.User;
import com.yjc.snaphive.model.vo.UserHighestScoreVO;
import com.yjc.snaphive.service.SnakeGameService;
import com.yjc.snaphive.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/snake")
public class SnakeGameController {

    @Resource
    private SnakeGameService snakeGameService;

    @Resource
    private UserService userService;

    /**
     * 保存游戏记录
     */
    @PostMapping("/save")
    public BaseResponse<SnakeGameRecord> saveGameRecord(@RequestBody SaveGameRecordRequest request,
                                                        HttpServletRequest httpServletRequest) {
        User loginUser = userService.getLoginUser(httpServletRequest);
        SnakeGameRecord record = snakeGameService.saveGameRecord(request, loginUser);
        return ResultUtils.success(record);
    }

    /**
     * 获取用户所有模式最高分
     */
    @GetMapping("/highest/all")
    public BaseResponse<UserHighestScoreVO> getUserAllHighestScores(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        UserHighestScoreVO scores = snakeGameService.getUserAllHighestScores(loginUser.getId());
        return ResultUtils.success(scores);
    }

    /**
     * 获取排行榜
     */
    @PostMapping("/ranking")
    public BaseResponse<List<SnakeGameRecord>> getRankingList(@RequestBody GameRankingRequest request) {
        List<SnakeGameRecord> rankingList = snakeGameService.getRankingList(request);
        return ResultUtils.success(rankingList);
    }
}
