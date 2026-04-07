package com.yjc.snaphive.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yjc.snaphive.common.BaseResponse;
import com.yjc.snaphive.common.ResultUtils;
import com.yjc.snaphive.exception.BusinessException;
import com.yjc.snaphive.exception.ErrorCode;
import com.yjc.snaphive.model.entity.LoveBoard;
import com.yjc.snaphive.model.entity.User;
import com.yjc.snaphive.service.LoveBoardService;
import com.yjc.snaphive.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 恋爱画板接口
 */
@RestController
@RequestMapping("/love-board")
@Slf4j
public class LoveBoardController {

    @Resource
    private LoveBoardService loveBoardService;

    @Resource
    private UserService userService;

    // region 增删改查

    /**
     * 创建恋爱画板
     *
     * @param loveBoard
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addLoveBoard(@RequestBody LoveBoard loveBoard, HttpServletRequest request) {
        if (loveBoard == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        long loveBoardId = loveBoardService.createLoveBoard(loveBoard, loginUser.getId());
        return ResultUtils.success(loveBoardId);
    }

    /**
     * 删除恋爱画板
     *
     * @param id
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteLoveBoard(@RequestParam("id") long id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        boolean result = loveBoardService.deleteLoveBoard(id, loginUser.getId());
        return ResultUtils.success(result);
    }

    /**
     * 更新恋爱画板
     *
     * @param loveBoard
     * @param request
     * @return
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updateLoveBoard(@RequestBody LoveBoard loveBoard, HttpServletRequest request) {
        if (loveBoard == null || loveBoard.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        boolean result = loveBoardService.updateLoveBoard(loveBoard, loginUser.getId());
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取恋爱画板
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    public BaseResponse<LoveBoard> getLoveBoardById(@RequestParam("id") long id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 获取当前登录用户（如果有）
        User loginUser = userService.isLogin(request);
        Long loginUserId = loginUser != null ? loginUser.getId() : null;
        LoveBoard loveBoard = loveBoardService.getLoveBoardById(id, loginUserId);
        return ResultUtils.success(loveBoard);
    }

    /**
     * 获取当前用户的恋爱画板
     *
     * @param request
     * @return
     */
    @GetMapping("/my")
    public BaseResponse<LoveBoard> getMyLoveBoard(HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        QueryWrapper<LoveBoard> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userId", loginUser.getId());
        queryWrapper.eq("isDelete", 0);
        LoveBoard loveBoard = loveBoardService.getOne(queryWrapper);
        return ResultUtils.success(loveBoard);
    }

    // endregion
}
