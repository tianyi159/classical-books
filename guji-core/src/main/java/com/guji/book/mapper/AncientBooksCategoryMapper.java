package com.guji.book.mapper;

import java.util.List;
import com.guji.book.domain.AncientBooksCategory;

/**
 * 古籍类型分类Mapper接口
 * 
 * @author ruoyi
 * @date 2025-02-16
 */
public interface AncientBooksCategoryMapper 
{
    /**
     * 查询古籍类型分类
     * 
     * @param categoryId 古籍类型分类主键
     * @return 古籍类型分类
     */
    public AncientBooksCategory selectAncientBooksCategoryByCategoryId(Long categoryId);

    /**
     * 查询古籍类型分类列表
     * 
     * @param ancientBooksCategory 古籍类型分类
     * @return 古籍类型分类集合
     */
    public List<AncientBooksCategory> selectAncientBooksCategoryList(AncientBooksCategory ancientBooksCategory);

    /**
     * 新增古籍类型分类
     * 
     * @param ancientBooksCategory 古籍类型分类
     * @return 结果
     */
    public int insertAncientBooksCategory(AncientBooksCategory ancientBooksCategory);

    /**
     * 修改古籍类型分类
     * 
     * @param ancientBooksCategory 古籍类型分类
     * @return 结果
     */
    public int updateAncientBooksCategory(AncientBooksCategory ancientBooksCategory);

    /**
     * 删除古籍类型分类
     * 
     * @param categoryId 古籍类型分类主键
     * @return 结果
     */
    public int deleteAncientBooksCategoryByCategoryId(Long categoryId);

    /**
     * 批量删除古籍类型分类
     * 
     * @param categoryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAncientBooksCategoryByCategoryIds(Long[] categoryIds);
}
