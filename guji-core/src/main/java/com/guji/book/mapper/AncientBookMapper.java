package com.guji.book.mapper;

import java.util.List;
import com.guji.book.domain.AncientBook;

/**
 * 古籍Mapper接口
 * 
 * @author chen
 * @date 2025-01-17
 */
public interface AncientBookMapper 
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
     * 删除古籍
     * 
     * @param bookId 古籍主键
     * @return 结果
     */
    public int deleteAncientBookByBookId(Long bookId);

    /**
     * 批量删除古籍
     * 
     * @param bookIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAncientBookByBookIds(Long[] bookIds);
}
