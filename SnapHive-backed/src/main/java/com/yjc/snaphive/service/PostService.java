package com.yjc.snaphive.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.snaphive.model.dto.post.PostAddRequest;
import com.yjc.snaphive.model.dto.post.PostQueryRequest;
import com.yjc.snaphive.model.entity.Post;
import com.yjc.snaphive.model.entity.User;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface PostService extends IService<Post> {
    /**
     * 发布帖子
     */
    Long addPost(PostAddRequest postAddRequest, User loginUser);


    /**
     * 分页获取帖子列表
     */
    Page<Post> listPosts(PostQueryRequest request, User loginUser);

    /**
     * 审核帖子
     */
    void reviewPost(Long postId, Integer status, String message, User loginUser);

    Page<Post> listMyPosts(PostQueryRequest postQueryRequest);

    boolean updatePost(Post post);

    /**
     * 获取关注用户的帖子列�?
     */
    Page<Post> getFollowPosts(HttpServletRequest request, PostQueryRequest postQueryRequest);

    /**
     * 获取帖子榜单
     * @param id 榜单类型�?-日榜 2-周榜 3-月榜 4-总榜
     */
    List<Post> getTop100Post(Long id);

    void fillPostInfo(Post post);

    /**
     * 获取帖子浏览�?
     */
    long getViewCount(Long postId);

    /**
     * 获取帖子详情（带浏览量统计）
     */
    Post getPostDetail(Long id, User loginUser, HttpServletRequest request);
}
