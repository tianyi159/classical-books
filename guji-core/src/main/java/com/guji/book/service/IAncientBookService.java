package com.guji.book.service;

import java.util.List;
import com.guji.book.domain.AncientBook;

/**
 * 古籍Service接口
 * 
 * @author chen
 * @date 2025-01-17
 */
public interface IAncientBookService 
{
    /**
     * 查询古籍
     * 
     * @param bookId 古籍主键
     * @return 古籍
     */
    public AncientBook selectAncientBookByBookId(Long bookId);

    /**
     * 查询古籍列表
     * 
     * @param ancientBook 古籍
     * @return 古籍集合
     */
    public List<AncientBook> selectAncientBookList(AncientBook ancientBook);

    /**
     * 新增古籍
     * 
     * @param ancientBook 古籍
     * @return 结果
     */
    public int insertAncientBook(AncientBook ancientBook);

    /**
     * 修改古籍
     * 
     * @param ancientBook 古籍
     * @return 结果
     */
    public int updateAncientBook(AncientBook ancientBook);

    /**
     * 批量删除古籍
     * 
     * @param bookIds 需要删除的古籍主键集合
     * @return 结果
     */
    public int deleteAncientBookByBookIds(Long[] bookIds);

    /**
     * 删除古籍信息
     * 
     * @param bookId 古籍主键
     * @return 结果
     */
    public int deleteAncientBookByBookId(Long bookId);
}
