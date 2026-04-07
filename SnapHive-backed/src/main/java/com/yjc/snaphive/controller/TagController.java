package com.yjc.snaphive.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjc.snaphive.annotation.AuthCheck;
import com.yjc.snaphive.common.BaseResponse;
import com.yjc.snaphive.common.PageRequest;
import com.yjc.snaphive.common.ResultUtils;
import com.yjc.snaphive.constant.UserConstant;
import com.yjc.snaphive.exception.ErrorCode;
import com.yjc.snaphive.exception.ThrowUtils;
import com.yjc.snaphive.model.entity.Tag;
import com.yjc.snaphive.model.vo.TagVO;
import com.yjc.snaphive.service.TagService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {
    @Resource
    private TagService tagService;
    /**
     * 获取所有标�?
     */
    @PostMapping("list/page/vo")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Page<TagVO>> listTagVOByPage(@RequestBody PageRequest pageRequest){
        long current = pageRequest.getCurrent();
        long pageSize = pageRequest.getPageSize();
        Page<Tag> tagPage = tagService.page(new Page<>(current, pageSize));
        Page<TagVO> tagVOPage = new Page<>(current, pageSize,tagPage.getTotal());
        List<TagVO> tagVOList = tagService.listTagVOByPage(tagPage.getRecords());
        tagVOPage.setRecords(tagVOList);
        return ResultUtils.success(tagVOPage);
    }

    /**
     * 添加标签
     */
    @PostMapping("/add")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> addTag(String tagName){
        ThrowUtils.throwIf(tagName == null || tagName.length() == 0, ErrorCode.PARAMS_ERROR);
        return ResultUtils.success(tagService.addTag(tagName));
    }

    /**
     * 删除标签
     */
    @PostMapping("/delete")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteTag(Long id){
        ThrowUtils.throwIf(id == null, ErrorCode.NOT_FOUND_ERROR);
        return ResultUtils.success(tagService.deleteTag(id));
    }

    /**
     * 查找标签
     */
    @PostMapping("/search")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<List<TagVO>> searchTag(String tagName){
        ThrowUtils.throwIf(tagName == null || tagName.length() == 0, ErrorCode.NOT_FOUND_ERROR);
        return ResultUtils.success(tagService.searchTag(tagName));
    }

}
