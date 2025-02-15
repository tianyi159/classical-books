package com.guji.book.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guji.book.mapper.AncientBookMapper;
import com.guji.book.domain.AncientBook;
import com.guji.book.service.IAncientBookService;

/**
 * 古籍Service业务层处理
 * 
 * @author chen
 * @date 2025-01-17
 */
@Service
public class AncientBookServiceImpl implements IAncientBookService 
{
    @Autowired
    private AncientBookMapper ancientBookMapper;

    /**
     * 查询古籍
     * 
     * @param bookId 古籍主键
     * @return 古籍
     */
    @Override
    public AncientBook selectAncientBookByBookId(Long bookId)
    {
        return ancientBookMapper.selectAncientBookByBookId(bookId);
    }

    /**
     * 查询古籍列表
     * 
     * @param ancientBook 古籍
     * @return 古籍
     */
    @Override
    public List<AncientBook> selectAncientBookList(AncientBook ancientBook)
    {
        return ancientBookMapper.selectAncientBookList(ancientBook);
    }

    /**
     * 新增古籍
     * 
     * @param ancientBook 古籍
     * @return 结果
     */
    @Override
    public int insertAncientBook(AncientBook ancientBook)
    {
        return ancientBookMapper.insertAncientBook(ancientBook);
    }

    /**
     * 修改古籍
     * 
     * @param ancientBook 古籍
     * @return 结果
     */
    @Override
    public int updateAncientBook(AncientBook ancientBook)
    {
        return ancientBookMapper.updateAncientBook(ancientBook);
    }

    /**
     * 批量删除古籍
     * 
     * @param bookIds 需要删除的古籍主键
     * @return 结果
     */
    @Override
    public int deleteAncientBookByBookIds(Long[] bookIds)
    {
        return ancientBookMapper.deleteAncientBookByBookIds(bookIds);
    }

    /**
     * 删除古籍信息
     * 
     * @param bookId 古籍主键
     * @return 结果
     */
    @Override
    public int deleteAncientBookByBookId(Long bookId)
    {
        return ancientBookMapper.deleteAncientBookByBookId(bookId);
    }
}
