package com.yjc.snaphive.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yjc.snaphive.model.entity.Message;
import com.yjc.snaphive.model.vo.MessageVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author SnapHive
 * @description 针对表【message(留言板表)】的数据库操作Mapper
 * @createDate 2025-01-03 16:28:14
 * @Entity generator.domain.Message
 */
public interface MessageMapper extends BaseMapper<Message> {
    @Select("select id,content,createTime from message order by createTime desc limit 500")
    List<MessageVO> getTop500();
}
