package com.yjc.snaphive.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjc.snaphive.model.entity.TimeAlbum;
import org.apache.ibatis.annotations.Mapper;

/**
 * 时光相册数据库操作
 */
@Mapper
public interface TimeAlbumMapper extends BaseMapper<TimeAlbum> {
}
