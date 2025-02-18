package com.guji.book.service.impl;

import java.util.Date;
import java.util.List;
import com.guji.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guji.book.mapper.AncientBookViewsMapper;
import com.guji.book.domain.AncientBookViews;
import com.guji.book.service.IAncientBookViewsService;

/**
 * 古籍浏览Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-18
 */
@Service
public class AncientBookViewsServiceImpl implements IAncientBookViewsService 
{
    @Autowired
    private AncientBookViewsMapper ancientBookViewsMapper;

    /**
     * 查询古籍浏览
     * 
     * @param id 古籍浏览主键
     * @return 古籍浏览
     */
    @Override
    public AncientBookViews selectAncientBookViewsById(Long id)
    {
        return ancientBookViewsMapper.selectAncientBookViewsById(id);
    }
    @Override
    public Boolean controllerViews(AncientBookViews ancientBookViews)
    {
        //        根据书本编号查询浏览量
        AncientBookViews ancientBookViews1 =selectAncientBookViewsByAncientBookId(ancientBookViews.getAncientBookId());
        if(ancientBookViews1!=null) {
            ancientBookViews1.setUpdateTime(new Date());
            ancientBookViews1.setViews(ancientBookViews1.getViews() + 1);
            updateAncientBookViews(ancientBookViews1);
        }else{
//            添加
            ancientBookViews.setViews(1L);
            ancientBookViews.setUpdateTime(new Date());
            insertAncientBookViews(ancientBookViews);
        }
        return true;
    }

    @Override
    public AncientBookViews selectAncientBookViewsByAncientBookId(Long ancientBookId) {
        return ancientBookViewsMapper.selectAncientBookViewsByAncientBookId(ancientBookId);
    }

    @Override
    public List<AncientBookViews> selectAncientBookViewsDesc()
    {
        return ancientBookViewsMapper.selectAncientBookViewsDesc();
    }

    /**
     * 查询古籍浏览列表
     * 
     * @param ancientBookViews 古籍浏览
     * @return 古籍浏览
     */
    @Override
    public List<AncientBookViews> selectAncientBookViewsList(AncientBookViews ancientBookViews)
    {
        return ancientBookViewsMapper.selectAncientBookViewsList(ancientBookViews);
    }

    /**
     * 新增古籍浏览
     * 
     * @param ancientBookViews 古籍浏览
     * @return 结果
     */
    @Override
    public int insertAncientBookViews(AncientBookViews ancientBookViews)
    {
        return ancientBookViewsMapper.insertAncientBookViews(ancientBookViews);
    }

    /**
     * 修改古籍浏览
     * 
     * @param ancientBookViews 古籍浏览
     * @return 结果
     */
    @Override
    public int updateAncientBookViews(AncientBookViews ancientBookViews)
    {
        ancientBookViews.setUpdateTime(DateUtils.getNowDate());
        return ancientBookViewsMapper.updateAncientBookViews(ancientBookViews);
    }

    /**
     * 批量删除古籍浏览
     * 
     * @param ids 需要删除的古籍浏览主键
     * @return 结果
     */
    @Override
    public int deleteAncientBookViewsByIds(Long[] ids)
    {
        return ancientBookViewsMapper.deleteAncientBookViewsByIds(ids);
    }

    /**
     * 删除古籍浏览信息
     * 
     * @param id 古籍浏览主键
     * @return 结果
     */
    @Override
    public int deleteAncientBookViewsById(Long id)
    {
        return ancientBookViewsMapper.deleteAncientBookViewsById(id);
    }
}
