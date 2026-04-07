package com.yjc.snaphive.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yjc.snaphive.mapper.TagMapper;
import com.yjc.snaphive.model.entity.Tag;
import com.yjc.snaphive.model.vo.TagVO;
import com.yjc.snaphive.service.TagService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author SnapHive
 * @description 针对表【tag(标签)】的数据库操作Service实现
 * @createDate 2024-12-13 17:37:29
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public List<String> listTag() {
        return this.baseMapper.listTag();
    }

    @Override
    public TagVO getTagVO(Tag tag) {
        if (tag == null) {
            return null;
        }
        TagVO tagVO = new TagVO();
        BeanUtil.copyProperties(tag, tagVO);
        return tagVO;
    }

    @Override
    public List<TagVO> listTagVOByPage(List<Tag> records) {
        if (CollUtil.isEmpty(records)) {
            return null;
        }
        return records.stream().map(this::getTagVO).collect(Collectors.toList());
    }

    @Override
    public Boolean addTag(String tagName) {
        Tag tag = new Tag();
        tag.setTagName(tagName);
        return save(tag);
    }

    @Override
    public Boolean deleteTag(Long id) {
        return removeById(id);
    }

    @Override
    public List<TagVO> searchTag(String tagName) {
        // 创建查询条件包装�?
        QueryWrapper<Tag> queryWrapper = new QueryWrapper<>();
        // 使用like进行模糊查询，匹配标签名称包含输入的tagName的记�?
        queryWrapper.like("tagName", tagName);
        // 从数据库中查询符合条件的Tag实体列表
        List<Tag> tagList = baseMapper.selectList(queryWrapper);
        // 将查询到的Tag实体列表转换为TagVO列表并返�?
        return listTagVOByPage(tagList);
    }
}
