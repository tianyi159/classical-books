package com.guji.book.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.guji.book.mapper.AncientBooksCategoryMapper;
import com.guji.book.domain.AncientBooksCategory;
import com.guji.book.service.IAncientBooksCategoryService;

/**
 * 古籍类型分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-16
 */
@Service
public class AncientBooksCategoryServiceImpl implements IAncientBooksCategoryService 
{
    @Autowired
    private AncientBooksCategoryMapper ancientBooksCategoryMapper;

    /**
     * 查询古籍类型分类
     * 
     * @param categoryId 古籍类型分类主键
     * @return 古籍类型分类
     */
    @Override
    public AncientBooksCategory selectAncientBooksCategoryByCategoryId(Long categoryId)
    {
        return ancientBooksCategoryMapper.selectAncientBooksCategoryByCategoryId(categoryId);
    }

    /**
     * 查询古籍类型分类列表
     * 
     * @param ancientBooksCategory 古籍类型分类
     * @return 古籍类型分类
     */
    @Override
    public List<AncientBooksCategory> selectAncientBooksCategoryList(AncientBooksCategory ancientBooksCategory)
    {
        return ancientBooksCategoryMapper.selectAncientBooksCategoryList(ancientBooksCategory);
    }

    /**
     * 新增古籍类型分类
     * 
     * @param ancientBooksCategory 古籍类型分类
     * @return 结果
     */
    @Override
    public int insertAncientBooksCategory(AncientBooksCategory ancientBooksCategory)
    {
        return ancientBooksCategoryMapper.insertAncientBooksCategory(ancientBooksCategory);
    }

    /**
     * 修改古籍类型分类
     * 
     * @param ancientBooksCategory 古籍类型分类
     * @return 结果
     */
    @Override
    public int updateAncientBooksCategory(AncientBooksCategory ancientBooksCategory)
    {
        return ancientBooksCategoryMapper.updateAncientBooksCategory(ancientBooksCategory);
    }

    /**
     * 批量删除古籍类型分类
     * 
     * @param categoryIds 需要删除的古籍类型分类主键
     * @return 结果
     */
    @Override
    public int deleteAncientBooksCategoryByCategoryIds(Long[] categoryIds)
    {
        return ancientBooksCategoryMapper.deleteAncientBooksCategoryByCategoryIds(categoryIds);
    }

    /**
     * 删除古籍类型分类信息
     * 
     * @param categoryId 古籍类型分类主键
     * @return 结果
     */
    @Override
    public int deleteAncientBooksCategoryByCategoryId(Long categoryId)
    {
        return ancientBooksCategoryMapper.deleteAncientBooksCategoryByCategoryId(categoryId);
    }
}
