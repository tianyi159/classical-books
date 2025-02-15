package com.guji.book.service;

import java.util.List;
import com.guji.book.domain.AncientPoem;

/**
 * 古诗Service接口
 * 
 * @author chen
 * @date 2025-01-17
 */
public interface IAncientPoemService 
{
    /**
     * 查询古诗
     * 
     * @param ariId 古诗主键
     * @return 古诗
     */
    public AncientPoem selectAncientPoemByAriId(Long ariId);

    /**
     * 查询古诗列表
     * 
     * @param ancientPoem 古诗
     * @return 古诗集合
     */
    public List<AncientPoem> selectAncientPoemList(AncientPoem ancientPoem);

    /**
     * 新增古诗
     * 
     * @param ancientPoem 古诗
     * @return 结果
     */
    public int insertAncientPoem(AncientPoem ancientPoem);

    /**
     * 修改古诗
     * 
     * @param ancientPoem 古诗
     * @return 结果
     */
    public int updateAncientPoem(AncientPoem ancientPoem);

    /**
     * 批量删除古诗
     * 
     * @param ariIds 需要删除的古诗主键集合
     * @return 结果
     */
    public int deleteAncientPoemByAriIds(Long[] ariIds);

    /**
     * 删除古诗信息
     * 
     * @param ariId 古诗主键
     * @return 结果
     */
    public int deleteAncientPoemByAriId(Long ariId);
}
