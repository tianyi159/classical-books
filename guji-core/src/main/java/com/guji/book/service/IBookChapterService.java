package com.guji.book.service;

import java.util.List;
import com.guji.book.domain.BookChapter;

/**
 * 古籍章节Service接口
 * 
 * @author chen
 * @date 2025-01-17
 */
public interface IBookChapterService 
{
    /**
     * 查询古籍章节
     * 
     * @param chapterId 古籍章节主键
     * @return 古籍章节
     */
    public BookChapter selectBookChapterByChapterId(Long chapterId);

    /**
     * 查询古籍章节列表
     * 
     * @param bookChapter 古籍章节
     * @return 古籍章节集合
     */
    public List<BookChapter> selectBookChapterList(BookChapter bookChapter);

    /**
     * 新增古籍章节
     * 
     * @param bookChapter 古籍章节
     * @return 结果
     */
    public int insertBookChapter(BookChapter bookChapter);

    /**
     * 修改古籍章节
     * 
     * @param bookChapter 古籍章节
     * @return 结果
     */
    public int updateBookChapter(BookChapter bookChapter);

    /**
     * 批量删除古籍章节
     * 
     * @param chapterIds 需要删除的古籍章节主键集合
     * @return 结果
     */
    public int deleteBookChapterByChapterIds(Long[] chapterIds);

    /**
     * 删除古籍章节信息
     * 
     * @param chapterId 古籍章节主键
     * @return 结果
     */
    public int deleteBookChapterByChapterId(Long chapterId);
}
