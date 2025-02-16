package com.guji.book.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guji.common.annotation.Excel;
import com.guji.common.core.domain.BaseEntity;

/**
 * 古籍类型分类对象 ancient_books_category
 * 
 * @author ruoyi
 * @date 2025-02-16
 */
public class AncientBooksCategory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 分类ID，主键，自动递增 */
    private Long categoryId;

    /** 分类名称，不能为空 */
    @Excel(name = "分类名称，不能为空")
    private String categoryName;

    /** 古籍类型 */
    @Excel(name = "古籍类型")
    private Long type;

    public void setCategoryId(Long categoryId) 
    {
        this.categoryId = categoryId;
    }

    public Long getCategoryId() 
    {
        return categoryId;
    }
    public void setCategoryName(String categoryName) 
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName() 
    {
        return categoryName;
    }
    public void setType(Long type) 
    {
        this.type = type;
    }

    public Long getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("categoryId", getCategoryId())
            .append("categoryName", getCategoryName())
            .append("type", getType())
            .toString();
    }
}
