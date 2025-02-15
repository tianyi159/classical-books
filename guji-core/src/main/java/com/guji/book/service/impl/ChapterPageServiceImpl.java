package com.guji.book.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guji.book.mapper.ChapterPageMapper;
import com.guji.book.domain.ChapterPage;
import com.guji.book.service.IChapterPageService;

/**
 * 章节页面Service业务层处理
 * 
 * @author chen
 * @date 2025-01-17
 */
@Service
public class ChapterPageServiceImpl implements IChapterPageService 
{
    @Autowired
    private ChapterPageMapper chapterPageMapper;

    /**
     * 查询章节页面
     * 
     * @param pageId 章节页面主键
     * @return 章节页面
     */
    @Override
    public ChapterPage selectChapterPageByPageId(Long pageId)
    {
        return chapterPageMapper.selectChapterPageByPageId(pageId);
    }

    /**
     * 查询章节页面列表
     * 
     * @param chapterPage 章节页面
     * @return 章节页面
     */
    @Override
    public List<ChapterPage> selectChapterPageList(ChapterPage chapterPage)
    {
        return chapterPageMapper.selectChapterPageList(chapterPage);
    }

    /**
     * 新增章节页面
     * 
     * @param chapterPage 章节页面
     * @return 结果
     */
    @Override
    public int insertChapterPage(ChapterPage chapterPage)
    {
        return chapterPageMapper.insertChapterPage(chapterPage);
    }

    /**
     * 修改章节页面
     * 
     * @param chapterPage 章节页面
     * @return 结果
     */
    @Override
    public int updateChapterPage(ChapterPage chapterPage)
    {
        return chapterPageMapper.updateChapterPage(chapterPage);
    }

    /**
     * 批量删除章节页面
     * 
     * @param pageIds 需要删除的章节页面主键
     * @return 结果
     */
    @Override
    public int deleteChapterPageByPageIds(Long[] pageIds)
    {
        return chapterPageMapper.deleteChapterPageByPageIds(pageIds);
    }

    /**
     * 删除章节页面信息
     * 
     * @param pageId 章节页面主键
     * @return 结果
     */
    @Override
    public int deleteChapterPageByPageId(Long pageId)
    {
        return chapterPageMapper.deleteChapterPageByPageId(pageId);
    }
}
