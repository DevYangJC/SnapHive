package com.yjc.snaphive.controller;

import com.yjc.snaphive.service.IDeepSeekService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjc.snaphive.model.vo.AiChatVO;

@RestController
@RequestMapping("/deepseek")
public class AiChatController {

    @Resource
    private IDeepSeekService deepSeekService;

    @PostMapping("/send")
    public String send(String query, HttpServletRequest request) {
        return deepSeekService.generateResponse(query, request);
    }

    @PostMapping("/history")
    public Page<AiChatVO> getChatHistory(
            @RequestParam(defaultValue = "1") int current,
            @RequestParam(defaultValue = "20") int pageSize,
            HttpServletRequest request) {
        return deepSeekService.getChatHistory(request, current, pageSize);
    }
}
