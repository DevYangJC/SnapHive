package com.yjc.snaphive.service;

import javax.servlet.http.HttpServletRequest;
import com.yjc.snaphive.model.vo.AiChatVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

public interface IDeepSeekService {

    /**
     * 提问生成回复
     */
    String generateResponse(String query, HttpServletRequest request);

    Page<AiChatVO> getChatHistory(HttpServletRequest request, int current, int pageSize);

}
