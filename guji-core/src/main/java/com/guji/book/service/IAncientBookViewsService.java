package com.guji.book.service;

import java.util.List;
import com.guji.book.domain.AncientBookViews;

/**
 * 古籍浏览Service接口
 * 
 * @author ruoyi
 * @date 2025-02-18
 */
public interface IAncientBookViewsService 
{
    /**
     * 查询古籍浏览
     * 
     * @param id 古籍浏览主键
     * @return 古籍浏览
     */
    public AncientBookViews selectAncientBookViewsById(Long id);

    Boolean controllerViews(AncientBookViews ancientBookViews);

    public AncientBookViews selectAncientBookViewsByAncientBookId(Long ancientBookId);
    public List<AncientBookViews> selectAncientBookViewsDesc();

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
     * 批量删除古籍浏览
     * 
     * @param ids 需要删除的古籍浏览主键集合
     * @return 结果
     */
    public int deleteAncientBookViewsByIds(Long[] ids);

    /**
     * 删除古籍浏览信息
     * 
     * @param id 古籍浏览主键
     * @return 结果
     */
    public int deleteAncientBookViewsById(Long id);
}
