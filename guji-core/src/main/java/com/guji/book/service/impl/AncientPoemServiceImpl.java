package com.guji.book.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guji.book.mapper.AncientPoemMapper;
import com.guji.book.domain.AncientPoem;
import com.guji.book.service.IAncientPoemService;

/**
 * 古诗Service业务层处理
 * 
 * @author chen
 * @date 2025-01-17
 */
@Service
public class AncientPoemServiceImpl implements IAncientPoemService 
{
    @Autowired
    private AncientPoemMapper ancientPoemMapper;

    /**
     * 查询古诗
     * 
     * @param ariId 古诗主键
     * @return 古诗
     */
    @Override
    public AncientPoem selectAncientPoemByAriId(Long ariId)
    {
        return ancientPoemMapper.selectAncientPoemByAriId(ariId);
    }

    /**
     * 查询古诗列表
     * 
     * @param ancientPoem 古诗
     * @return 古诗
     */
    @Override
    public List<AncientPoem> selectAncientPoemList(AncientPoem ancientPoem)
    {
        return ancientPoemMapper.selectAncientPoemList(ancientPoem);
    }

    /**
     * 新增古诗
     * 
     * @param ancientPoem 古诗
     * @return 结果
     */
    @Override
    public int insertAncientPoem(AncientPoem ancientPoem)
    {
        return ancientPoemMapper.insertAncientPoem(ancientPoem);
    }

    /**
     * 修改古诗
     * 
     * @param ancientPoem 古诗
     * @return 结果
     */
    @Override
    public int updateAncientPoem(AncientPoem ancientPoem)
    {
        return ancientPoemMapper.updateAncientPoem(ancientPoem);
    }

    /**
     * 批量删除古诗
     * 
     * @param ariIds 需要删除的古诗主键
     * @return 结果
     */
    @Override
    public int deleteAncientPoemByAriIds(Long[] ariIds)
    {
        return ancientPoemMapper.deleteAncientPoemByAriIds(ariIds);
    }

    /**
     * 删除古诗信息
     * 
     * @param ariId 古诗主键
     * @return 结果
     */
    @Override
    public int deleteAncientPoemByAriId(Long ariId)
    {
        return ancientPoemMapper.deleteAncientPoemByAriId(ariId);
    }
}
