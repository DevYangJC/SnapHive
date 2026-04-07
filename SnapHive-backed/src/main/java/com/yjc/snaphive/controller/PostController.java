package com.yjc.snaphive.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjc.snaphive.common.BaseResponse;
import com.yjc.snaphive.common.ResultUtils;
import com.yjc.snaphive.exception.BusinessException;
import com.yjc.snaphive.exception.ErrorCode;
import com.yjc.snaphive.exception.ThrowUtils;
import com.yjc.snaphive.manager.CrawlerManager;
import com.yjc.snaphive.model.dto.post.PostAddRequest;
import com.yjc.snaphive.model.dto.post.PostQueryRequest;
import com.yjc.snaphive.model.entity.Post;
import com.yjc.snaphive.model.entity.User;
import com.yjc.snaphive.service.PostService;
import com.yjc.snaphive.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.yjc.snaphive.constant.RedisConstant;
import com.yjc.snaphive.service.LikeRecordService;
import com.yjc.snaphive.model.dto.like.LikeRequest;
import com.yjc.snaphive.constant.CrawlerConstant;

@RestController
@RequestMapping("/post")
@Slf4j
public class PostController {

    @Resource
    private PostService postService;

    @Resource
    private UserService userService;

    @Resource
    private LikeRecordService likeRecordService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Resource
    private CrawlerManager crawlerManager;

    /**
     * 创建帖子
     */
    @PostMapping("/add")
    public BaseResponse<Long> addPost(@RequestBody PostAddRequest postAddRequest, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        Long postId = postService.addPost(postAddRequest, loginUser);
        return ResultUtils.success(postId);
    }

    /**
     * 删除帖子
     */
    @PostMapping("/delete/{id}")
    public BaseResponse<Boolean> deletePost(@PathVariable Long id, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        // 判断是否存在
        Post post = postService.getById(id);
        ThrowUtils.throwIf(post == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        if (!post.getUserId().equals(loginUser.getId()) && !userService.isAdmin(loginUser)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean result = postService.removeById(id);
        return ResultUtils.success(result);
    }

    /**
     * 更新帖子
     */
    @PostMapping("/update")
    public BaseResponse<Boolean> updatePost(@RequestBody Post post, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        // 判断是否存在
        Post oldPost = postService.getById(post.getId());
        ThrowUtils.throwIf(oldPost == null, ErrorCode.NOT_FOUND_ERROR);

        // 仅本人或管理员可修改
        if (!oldPost.getUserId().equals(loginUser.getId()) && !userService.isAdmin(loginUser)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }

        boolean result = postService.updatePost(post);
        return ResultUtils.success(result);
    }

    /**
     * 根据 id 获取帖子
     */
    @GetMapping("/get/{id}")
    public BaseResponse<Post> getPostById(@PathVariable Long id, HttpServletRequest request) {
        // 检测爬�?
        crawlerManager.detectNormalRequest(request);

        User loginUser = userService.getLoginUser(request);
        Post post = postService.getPostDetail(id, loginUser, request);
        return ResultUtils.success(post);
    }

    /**
     * 分页获取帖子列表
     */
    @PostMapping("/list/page")
    public BaseResponse<Page<Post>> listPostByPage(@RequestBody PostQueryRequest postQueryRequest,
                                                   HttpServletRequest request) {
        // 用户权限校验
        User loginUser = userService.isLogin(request);
        if (loginUser != null) {
            String userRole = loginUser.getUserRole();
            ThrowUtils.throwIf(userRole.equals(CrawlerConstant.BAN_ROLE),
                    ErrorCode.NO_AUTH_ERROR, "封禁用户禁止获取数据,请联系管理员");
        }

        // 限制爬虫
        long size = postQueryRequest.getPageSize();
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        crawlerManager.detectNormalRequest(request);

        Page<Post> postPage = postService.listPosts(postQueryRequest, loginUser);
        return ResultUtils.success(postPage);
    }

    /**
     * 点赞/取消点赞
     */
    @PostMapping("/like/{id}")
    public BaseResponse<Boolean> likePost(@PathVariable Long id, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        // 使用通用点赞服务
        LikeRequest likeRequest = new LikeRequest();
        likeRequest.setTargetId(id);
        likeRequest.setTargetType(2);  // 2表示帖子类型
        likeRequest.setIsLiked(true);  // 自动判断是点赞还是取�?

        likeRecordService.doLike(likeRequest, loginUser.getId());
        return ResultUtils.success(true);
    }

    /**
     * 审核帖子（仅管理员）
     */
    @PostMapping("/review/{id}")
    public BaseResponse<Boolean> reviewPost(@PathVariable Long id, @RequestParam Integer status,
                                            @RequestParam(required = false) String message, HttpServletRequest request) {
        User loginUser = userService.getLoginUser(request);
        postService.reviewPost(id, status, message, loginUser);
        return ResultUtils.success(true);
    }

    /**
     * 获取当前用户的所有帖�?
     */
    @PostMapping("/my/list")
    public BaseResponse<Page<Post>> listMyPosts(@RequestBody PostQueryRequest postQueryRequest,
                                                HttpServletRequest request) {
        // 获取当前登录用户
        User loginUser = userService.getLoginUser(request);
        ThrowUtils.throwIf(loginUser == null, ErrorCode.NOT_LOGIN_ERROR);

        // 设置只查询当前用户的帖子
        postQueryRequest.setUserId(loginUser.getId());
        Page<Post> postPage = postService.listMyPosts(postQueryRequest);
        return ResultUtils.success(postPage);
    }

    /**
     * 获取关注用户的帖子列�?
     */
    @PostMapping("/follow")
    public BaseResponse<Page<Post>> getFollowPosts(@RequestBody PostQueryRequest postQueryRequest,
                                                   HttpServletRequest request) {
        // 检测爬�?
        crawlerManager.detectNormalRequest(request);
        return ResultUtils.success(postService.getFollowPosts(request, postQueryRequest));
    }

    /**
     * 获取帖子榜单
     */
    @GetMapping("/top100/{id}")
    public BaseResponse<List<Post>> getTop100Post(@PathVariable Long id, HttpServletRequest request) {
        // 检测爬�?
        crawlerManager.detectNormalRequest(request);

        // 构建 Redis 缓存�?key
        String cacheKey = RedisConstant.TOP_100_POST_REDIS_KEY_PREFIX + id;

        // 先从 Redis 缓存中获取数�?
        String cachedValue = stringRedisTemplate.opsForValue().get(cacheKey);
        if (cachedValue != null) {
            List<Post> postList = JSONUtil.toList(cachedValue, Post.class);
            return ResultUtils.success(postList);
        }

        // 缓存未命中，调用服务层方法获取数�?
        List<Post> postList = postService.getTop100Post(id);

        // 设置缓存，添加随机过期时间防止缓存雪�?
        int cacheExpireTime = (int) (RedisConstant.TOP_100_POST_REDIS_KEY_EXPIRE_TIME
                + RandomUtil.randomInt(0, 6000));
        stringRedisTemplate.opsForValue().set(cacheKey, JSONUtil.toJsonStr(postList),
                cacheExpireTime, TimeUnit.SECONDS);

        return ResultUtils.success(postList);
    }
}
