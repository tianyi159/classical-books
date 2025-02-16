package com.guji.book.mapper;

import java.util.List;
import com.guji.book.domain.AncientBookBookmarks;

/**
 * 书签Mapper接口
 * 
 * @author ruoyi
 * @date 2025-02-16
 */
public interface AncientBookBookmarksMapper 
{
    /**
     * 查询书签
     * 
     * @param bookmarkId 书签主键
     * @return 书签
     */
    public AncientBookBookmarks selectAncientBookBookmarksByBookmarkId(Long bookmarkId);

    /**
     * 查询书签列表
     * 
     * @param ancientBookBookmarks 书签
     * @return 书签集合
     */
    public List<AncientBookBookmarks> selectAncientBookBookmarksList(AncientBookBookmarks ancientBookBookmarks);

    /**
     * 新增书签
     * 
     * @param ancientBookBookmarks 书签
     * @return 结果
     */
    public int insertAncientBookBookmarks(AncientBookBookmarks ancientBookBookmarks);

    /**
     * 修改书签
     * 
     * @param ancientBookBookmarks 书签
     * @return 结果
     */
    public int updateAncientBookBookmarks(AncientBookBookmarks ancientBookBookmarks);

    /**
     * 删除书签
     * 
     * @param bookmarkId 书签主键
     * @return 结果
     */
    public int deleteAncientBookBookmarksByBookmarkId(Long bookmarkId);

    /**
     * 批量删除书签
     * 
     * @param bookmarkIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAncientBookBookmarksByBookmarkIds(Long[] bookmarkIds);

}
