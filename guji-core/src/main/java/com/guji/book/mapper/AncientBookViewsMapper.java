package com.guji.book.mapper;

import java.util.List;
import com.guji.book.domain.AncientBookViews;

/**
 * 古籍浏览Mapper接口
 * 
 * @author ruoyi
 * @date 2025-02-18
 */
public interface AncientBookViewsMapper 
{
    /**
     * 查询古籍浏览
     * 
     * @param id 古籍浏览主键
     * @return 古籍浏览
     */
    public AncientBookViews selectAncientBookViewsById(Long id);

    /**
     * 查询古籍浏览列表
     * 
     * @param ancientBookViews 古籍浏览
     * @return 古籍浏览集合
     */
    public List<AncientBookViews> selectAncientBookViewsList(AncientBookViews ancientBookViews);

    /**
     * 新增古籍浏览
     * 
     * @param ancientBookViews 古籍浏览
     * @return 结果
     */
    public int insertAncientBookViews(AncientBookViews ancientBookViews);

    /**
     * 修改古籍浏览
     * 
     * @param ancientBookViews 古籍浏览
     * @return 结果
     */
    public int updateAncientBookViews(AncientBookViews ancientBookViews);

    /**
     * 删除古籍浏览
     * 
     * @param id 古籍浏览主键
     * @return 结果
     */
    public int deleteAncientBookViewsById(Long id);

    /**
     * 批量删除古籍浏览
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAncientBookViewsByIds(Long[] ids);

    List<AncientBookViews> selectAncientBookViewsDesc();

    AncientBookViews selectAncientBookViewsByAncientBookId(Long ancientBookId);
}
