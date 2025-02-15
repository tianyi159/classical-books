package com.guji.book.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guji.book.mapper.BookShelfMapper;
import com.guji.book.domain.BookShelf;
import com.guji.book.service.IBookShelfService;

/**
 * 收藏Service业务层处理
 * 
 * @author chen
 * @date 2025-01-19
 */
@Service
public class BookShelfServiceImpl implements IBookShelfService 
{
    @Autowired
    private BookShelfMapper bookShelfMapper;

    /**
     * 查询收藏
     * 
     * @param shelfId 收藏主键
     * @return 收藏
     */
    @Override
    public BookShelf selectBookShelfByShelfId(Long shelfId)
    {
        return bookShelfMapper.selectBookShelfByShelfId(shelfId);
    }

    /**
     * 查询收藏列表
     * 
     * @param bookShelf 收藏
     * @return 收藏
     */
    @Override
    public List<BookShelf> selectBookShelfList(BookShelf bookShelf)
    {
        return bookShelfMapper.selectBookShelfList(bookShelf);
    }

    /**
     * 新增收藏
     * 
     * @param bookShelf 收藏
     * @return 结果
     */
    @Override
    public int insertBookShelf(BookShelf bookShelf)
    {
        return bookShelfMapper.insertBookShelf(bookShelf);
    }

    /**
     * 修改收藏
     * 
     * @param bookShelf 收藏
     * @return 结果
     */
    @Override
    public int updateBookShelf(BookShelf bookShelf)
    {
        return bookShelfMapper.updateBookShelf(bookShelf);
    }

    /**
     * 批量删除收藏
     * 
     * @param shelfIds 需要删除的收藏主键
     * @return 结果
     */
    @Override
    public int deleteBookShelfByShelfIds(Long[] shelfIds)
    {
        return bookShelfMapper.deleteBookShelfByShelfIds(shelfIds);
    }

    /**
     * 删除收藏信息
     * 
     * @param shelfId 收藏主键
     * @return 结果
     */
    @Override
    public int deleteBookShelfByShelfId(Long shelfId)
    {
        return bookShelfMapper.deleteBookShelfByShelfId(shelfId);
    }
}
