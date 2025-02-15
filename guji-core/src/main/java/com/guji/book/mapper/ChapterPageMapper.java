package com.guji.book.mapper;

import java.util.List;
import com.guji.book.domain.ChapterPage;

/**
 * 章节页面Mapper接口
 * 
 * @author chen
 * @date 2025-01-17
 */
public interface ChapterPageMapper 
{
    /**
     * 查询章节页面
     * 
     * @param pageId 章节页面主键
     * @return 章节页面
     */
    public ChapterPage selectChapterPageByPageId(Long pageId);

    /**
     * 查询章节页面列表
     * 
     * @param chapterPage 章节页面
     * @return 章节页面集合
     */
    public List<ChapterPage> selectChapterPageList(ChapterPage chapterPage);

    /**
     * 新增章节页面
     * 
     * @param chapterPage 章节页面
     * @return 结果
     */
    public int insertChapterPage(ChapterPage chapterPage);

    /**
     * 修改章节页面
     * 
     * @param chapterPage 章节页面
     * @return 结果
     */
    public int updateChapterPage(ChapterPage chapterPage);

    /**
     * 删除章节页面
     * 
     * @param pageId 章节页面主键
     * @return 结果
     */
    public int deleteChapterPageByPageId(Long pageId);

    /**
     * 批量删除章节页面
     * 
     * @param pageIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChapterPageByPageIds(Long[] pageIds);
}
