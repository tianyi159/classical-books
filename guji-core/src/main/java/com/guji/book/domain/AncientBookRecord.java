package com.guji.book.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guji.common.annotation.Excel;
import com.guji.common.core.domain.BaseEntity;

/**
 * 阅读记录对象 ancient_book_record
 * 
 * @author ruoyi
 * @date 2025-02-16
 */
public class AncientBookRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 阅读记录ID，主键，自动递增 */
    private Long recordId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;
    private String userName;

    /** 古籍ID */
    @Excel(name = "古籍ID")
    private Long ancientBookId;
    private String ancientBookName;
    private String ancientBookUrl;
    private String author;

    @Excel(name = "类型")
    private Integer type;
    private String typeName;
    private Integer categoryId;

    private String categoryName;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

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

    /** 当前章节ID */
    @Excel(name = "当前章节ID")
    private Long chapterId;
    private String chapterName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAncientBookName() {
        return ancientBookName;
    }

    public void setAncientBookName(String ancientBookName) {
        this.ancientBookName = ancientBookName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    /** 最后更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastUpdated;

    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setAncientBookId(Long ancientBookId) 
    {
        this.ancientBookId = ancientBookId;
    }

    public Long getAncientBookId() 
    {
        return ancientBookId;
    }
    public void setChapterId(Long chapterId) 
    {
        this.chapterId = chapterId;
    }

    public Long getChapterId() 
    {
        return chapterId;
    }
    public void setLastUpdated(Date lastUpdated) 
    {
        this.lastUpdated = lastUpdated;
    }

    public Date getLastUpdated() 
    {
        return lastUpdated;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recordId", getRecordId())
            .append("userId", getUserId())
            .append("ancientBookId", getAncientBookId())
            .append("chapterId", getChapterId())
            .append("lastUpdated", getLastUpdated())
            .toString();
    }
}
