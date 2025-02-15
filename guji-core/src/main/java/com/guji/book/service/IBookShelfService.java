package com.guji.book.service;

import java.util.List;
import com.guji.book.domain.BookShelf;

/**
 * 收藏Service接口
 * 
 * @author chen
 * @date 2025-01-19
 */
public interface IBookShelfService 
{
    /**
     * 查询收藏
     * 
     * @param shelfId 收藏主键
     * @return 收藏
     */
    public BookShelf selectBookShelfByShelfId(Long shelfId);

    /**
     * 查询收藏列表
     * 
     * @param bookShelf 收藏
     * @return 收藏集合
     */
    public List<BookShelf> selectBookShelfList(BookShelf bookShelf);

    /**
     * 新增收藏
     * 
     * @param bookShelf 收藏
     * @return 结果
     */
    public int insertBookShelf(BookShelf bookShelf);

    /**
     * 修改收藏
     * 
     * @param bookShelf 收藏
     * @return 结果
     */
    public int updateBookShelf(BookShelf bookShelf);

    /**
     * 批量删除收藏
     * 
     * @param shelfIds 需要删除的收藏主键集合
     * @return 结果
     */
    public int deleteBookShelfByShelfIds(Long[] shelfIds);

    /**
     * 删除收藏信息
     * 
     * @param shelfId 收藏主键
     * @return 结果
     */
    public int deleteBookShelfByShelfId(Long shelfId);
}
