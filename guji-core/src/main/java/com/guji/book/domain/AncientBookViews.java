package com.guji.book.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guji.common.annotation.Excel;
import com.guji.common.core.domain.BaseEntity;

/**
 * 古籍浏览对象 ancient_book_views
 * 
 * @author ruoyi
 * @date 2025-02-18
 */
public class AncientBookViews extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 记录编号 */
    private Long id;

    /** 古籍编号 */
    @Excel(name = "古籍编号")
    private Long ancientBookId;
    private String ancientBookName;
    private String ancientBookUrl;
    private String author;

    public String getAncientBookUrl() {
        return ancientBookUrl;
    }

    public void setAncientBookUrl(String ancientBookUrl) {
        this.ancientBookUrl = ancientBookUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAncientBookName() {
        return ancientBookName;
    }

    public void setAncientBookName(String ancientBookName) {
        this.ancientBookName = ancientBookName;
    }

    /** 浏览量 */
    @Excel(name = "浏览量")
    private Long views;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAncientBookId(Long ancientBookId) 
    {
        this.ancientBookId = ancientBookId;
    }

    public Long getAncientBookId() 
    {
        return ancientBookId;
    }
    public void setViews(Long views) 
    {
        this.views = views;
    }

    public Long getViews() 
    {
        return views;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("ancientBookId", getAncientBookId())
            .append("views", getViews())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
