package com.guji.book.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guji.book.mapper.BookChapterMapper;
import com.guji.book.domain.BookChapter;
import com.guji.book.service.IBookChapterService;

/**
 * 古籍章节Service业务层处理
 * 
 * @author chen
 * @date 2025-01-17
 */
@Service
public class BookChapterServiceImpl implements IBookChapterService 
{
    @Autowired
    private BookChapterMapper bookChapterMapper;

    /**
     * 查询古籍章节
     * 
     * @param chapterId 古籍章节主键
     * @return 古籍章节
     */
    @Override
    public BookChapter selectBookChapterByChapterId(Long chapterId)
    {
        return bookChapterMapper.selectBookChapterByChapterId(chapterId);
    }

    /**
     * 查询古籍章节列表
     * 
     * @param bookChapter 古籍章节
     * @return 古籍章节
     */
    @Override
    public List<BookChapter> selectBookChapterList(BookChapter bookChapter)
    {
        return bookChapterMapper.selectBookChapterList(bookChapter);
    }

    /**
     * 新增古籍章节
     * 
     * @param bookChapter 古籍章节
     * @return 结果
     */
    @Override
    public int insertBookChapter(BookChapter bookChapter)
    {
        return bookChapterMapper.insertBookChapter(bookChapter);
    }

    /**
     * 修改古籍章节
     * 
     * @param bookChapter 古籍章节
     * @return 结果
     */
    @Override
    public int updateBookChapter(BookChapter bookChapter)
    {
        return bookChapterMapper.updateBookChapter(bookChapter);
    }

    /**
     * 批量删除古籍章节
     * 
     * @param chapterIds 需要删除的古籍章节主键
     * @return 结果
     */
    @Override
    public int deleteBookChapterByChapterIds(Long[] chapterIds)
    {
        return bookChapterMapper.deleteBookChapterByChapterIds(chapterIds);
    }

    /**
     * 删除古籍章节信息
     * 
     * @param chapterId 古籍章节主键
     * @return 结果
     */
    @Override
    public int deleteBookChapterByChapterId(Long chapterId)
    {
        return bookChapterMapper.deleteBookChapterByChapterId(chapterId);
    }
}
