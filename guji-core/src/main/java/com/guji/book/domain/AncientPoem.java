package com.guji.book.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guji.common.annotation.Excel;
import com.guji.common.core.domain.BaseEntity;

/**
 * 古诗对象 ancient_poem
 * 
 * @author chen
 * @date 2025-01-17
 */
public class AncientPoem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long ariId;

    /**  */
    @Excel(name = "")
    private String content;

    /** 朝代 */
    @Excel(name = "朝代")
    private String dynasty;
    private String dynastyName;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    /** 简介 */
    @Excel(name = "简介")
    private String introduce;

    /** 分类 */
    @Excel(name = "分类")
    private String category;
    private String categoryName;

    /** 合集 */
    @Excel(name = "合集")
    private String list;
    private String listName;

    public String getDynastyName() {
        return dynastyName;
    }

    public void setDynastyName(String dynastyName) {
        this.dynastyName = dynastyName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    public void setAriId(Long ariId) 
    {
        this.ariId = ariId;
    }

    public Long getAriId() 
    {
        return ariId;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setDynasty(String dynasty) 
    {
        this.dynasty = dynasty;
    }

    public String getDynasty() 
    {
        return dynasty;
    }
    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }
    public void setIntroduce(String introduce) 
    {
        this.introduce = introduce;
    }

    public String getIntroduce() 
    {
        return introduce;
    }
    public void setCategory(String category) 
    {
        this.category = category;
    }

    public String getCategory() 
    {
        return category;
    }
    public void setList(String list) 
    {
        this.list = list;
    }

    public String getList() 
    {
        return list;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("ariId", getAriId())
            .append("content", getContent())
            .append("dynasty", getDynasty())
            .append("author", getAuthor())
            .append("introduce", getIntroduce())
            .append("category", getCategory())
            .append("list", getList())
            .append("name", getName())
            .toString();
    }
}
