package com.yjc.snaphive.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.snaphive.model.dto.comments.CommentsAddRequest;
import com.yjc.snaphive.model.dto.comments.CommentsDeleteRequest;
import com.yjc.snaphive.model.dto.comments.CommentsLikeRequest;
import com.yjc.snaphive.model.dto.comments.CommentsQueryRequest;
import com.yjc.snaphive.model.entity.Comments;
import com.yjc.snaphive.model.vo.CommentsVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author SnapHive
 * @description 针对表【comments】的数据库操作Service
 * @createDate 2024-12-29 18:26:42
 */
public interface CommentsService extends IService<Comments> {

    Boolean addComment(CommentsAddRequest commentsAddRequest, HttpServletRequest request);


    Boolean deleteComment(CommentsDeleteRequest commentsDeleteRequest, HttpServletRequest request);


    Page<CommentsVO> queryComment(CommentsQueryRequest commentsQueryRequest, HttpServletRequest request);

    Boolean likeComment(CommentsLikeRequest commentslikeRequest, HttpServletRequest request);

    /**
     * 获取并清除用户未读的评论消息
     *
     * @param userId 用户ID
     * @return 未读的评论消息列�?
     */
    List<CommentsVO> getAndClearUnreadComments(Long userId);

    /**
     * 获取用户未读评论�?
     */
    long getUnreadCommentsCount(Long userId);

    /**
     * 清除用户所有未读评论状�?
     */
    void clearAllUnreadComments(Long userId);

    Page<CommentsVO> getCommentedHistory(CommentsQueryRequest commentsQueryRequest, Long id);

    Page<CommentsVO> getMyCommentHistory(CommentsQueryRequest commentsQueryRequest, Long id);
}
