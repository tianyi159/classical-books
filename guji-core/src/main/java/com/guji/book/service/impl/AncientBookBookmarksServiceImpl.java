package com.guji.book.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guji.book.mapper.AncientBookBookmarksMapper;
import com.guji.book.domain.AncientBookBookmarks;
import com.guji.book.service.IAncientBookBookmarksService;

/**
 * 书签Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-16
 */
@Service
public class AncientBookBookmarksServiceImpl implements IAncientBookBookmarksService 
{
    @Autowired
    private AncientBookBookmarksMapper ancientBookBookmarksMapper;

    /**
     * 查询书签
     * 
     * @param bookmarkId 书签主键
     * @return 书签
     */
    @Override
    public AncientBookBookmarks selectAncientBookBookmarksByBookmarkId(Long bookmarkId)
    {
        return ancientBookBookmarksMapper.selectAncientBookBookmarksByBookmarkId(bookmarkId);
    }

    /**
     * 查询书签列表
     * 
     * @param ancientBookBookmarks 书签
     * @return 书签
     */
    @Override
    public List<AncientBookBookmarks> selectAncientBookBookmarksList(AncientBookBookmarks ancientBookBookmarks)
    {
        return ancientBookBookmarksMapper.selectAncientBookBookmarksList(ancientBookBookmarks);
    }

    /**
     * 新增书签
     * 
     * @param ancientBookBookmarks 书签
     * @return 结果
     */
    @Override
    public int insertAncientBookBookmarks(AncientBookBookmarks ancientBookBookmarks)
    {
        return ancientBookBookmarksMapper.insertAncientBookBookmarks(ancientBookBookmarks);
    }

    /**
     * 修改书签
     * 
     * @param ancientBookBookmarks 书签
     * @return 结果
     */
    @Override
    public int updateAncientBookBookmarks(AncientBookBookmarks ancientBookBookmarks)
    {
        return ancientBookBookmarksMapper.updateAncientBookBookmarks(ancientBookBookmarks);
    }

    /**
     * 批量删除书签
     * 
     * @param bookmarkIds 需要删除的书签主键
     * @return 结果
     */
    @Override
    public int deleteAncientBookBookmarksByBookmarkIds(Long[] bookmarkIds)
    {
        return ancientBookBookmarksMapper.deleteAncientBookBookmarksByBookmarkIds(bookmarkIds);
    }

    /**
     * 删除书签信息
     * 
     * @param bookmarkId 书签主键
     * @return 结果
     */
    @Override
    public int deleteAncientBookBookmarksByBookmarkId(Long bookmarkId)
    {
        return ancientBookBookmarksMapper.deleteAncientBookBookmarksByBookmarkId(bookmarkId);
    }
}
