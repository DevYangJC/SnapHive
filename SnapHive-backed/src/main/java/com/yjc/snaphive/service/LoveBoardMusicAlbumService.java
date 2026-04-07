package com.yjc.snaphive.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yjc.snaphive.model.dto.musicalbum.MusicUploadRequest;
import com.yjc.snaphive.model.entity.LoveBoardMusicAlbum;
import com.yjc.snaphive.model.vo.AudioFileVO;

import java.util.List;

/**
 * 恋爱板音乐专栏服�?
 */
public interface LoveBoardMusicAlbumService extends IService<LoveBoardMusicAlbum> {

    /**
     * 创建音乐专栏
     *
     * @param loveBoardMusicAlbum 音乐专栏信息
     * @param userId 用户ID
     * @param loveBoardId 恋爱板ID
     * @return 专栏ID
     */
    long createMusicAlbum(LoveBoardMusicAlbum loveBoardMusicAlbum, Long userId, Long loveBoardId);

    /**
     * 删除音乐专栏
     *
     * @param id 专栏ID
     * @param userId 用户ID
     * @param loveBoardId 恋爱板ID
     * @return 是否成功
     */
    boolean deleteMusicAlbum(Long id, Long userId, Long loveBoardId);

    /**
     * 更新音乐专栏
     *
     * @param loveBoardMusicAlbum 音乐专栏信息
     * @param userId 用户ID
     * @param loveBoardId 恋爱板ID
     * @return 是否成功
     */
    boolean updateMusicAlbum(LoveBoardMusicAlbum loveBoardMusicAlbum, Long userId, Long loveBoardId);

    /**
     * 根据ID获取音乐专栏
     *
     * @param id 专栏ID
     * @param userId 用户ID（可选，用于权限检查）
     * @param password 访问密码（可选，用于私密专栏访问�?
     * @return 音乐专栏信息
     */
    LoveBoardMusicAlbum getMusicAlbumById(Long id, Long userId, String password);

    /**
     * 获取专栏内的音频列表
     *
     * @param albumId 专栏ID
     * @param userId 用户ID（可选，用于权限检查）
     * @param password 访问密码（可选，用于私密专栏访问�?
     * @return 音频列表
     */
    List<AudioFileVO> getAlbumAudios(Long albumId, Long userId, String password);

    /**
     * 分页获取恋爱板的音乐专栏列表
     *
     * @param loveBoardId 恋爱板ID
     * @param current 当前�?
     * @param pageSize 页大�?
     * @return 专栏列表
     */
    Page<LoveBoardMusicAlbum> listMusicAlbums(Long loveBoardId, Long current, Long pageSize);

    /**
     * 设置专栏密码
     *
     * @param albumId 专栏ID
     * @param newPassword 新密�?
     * @param userId 用户ID
     * @return 是否成功
     */
    boolean setAlbumPassword(Long albumId, String newPassword, Long userId);

    /**
     * 更新专栏密码
     *
     * @param albumId 专栏ID
     * @param oldPassword 旧密�?
     * @param newPassword 新密�?
     * @param userId 用户ID
     * @return 是否成功
     */
    boolean updateAlbumPassword(Long albumId, String oldPassword, String newPassword, Long userId);

    /**
     * 移除专栏密码
     *
     * @param albumId 专栏ID
     * @param oldPassword 旧密�?
     * @param userId 用户ID
     * @return 是否成功
     */
    boolean removeAlbumPassword(Long albumId, String oldPassword, Long userId);

    /**
     * 上传音乐到专�?
     *
     * @param musicUploadRequest 音乐上传请求
     * @param userId 用户ID
     * @return 音频信息
     */
    AudioFileVO uploadMusic(MusicUploadRequest musicUploadRequest, Long userId);

    /**
     * 删除专栏中的音频
     * @param albumId 专栏ID
     * @param audioId 音频ID
     * @param userId 用户ID
     * @return 是否删除成功
     */
    boolean deleteAudioFromAlbum(Long albumId, Long audioId, Long userId);

    /**
     * 检查专栏音频数量是否达到上�?
     * @param albumId 专栏ID
     * @return 是否达到上限
     */
    boolean checkAlbumCapacity(Long albumId);
}
