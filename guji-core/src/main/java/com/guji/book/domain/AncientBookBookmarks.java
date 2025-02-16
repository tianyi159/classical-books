package com.guji.book.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guji.common.annotation.Excel;
import com.guji.common.core.domain.BaseEntity;

/**
 * 书签对象 ancient_book_bookmarks
 * 
 * @author ruoyi
 * @date 2025-02-16
 */
public class AncientBookBookmarks extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 书签ID */
    private Long bookmarkId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;
    private String userName;

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

    /** 古籍ID */
    @Excel(name = "古籍ID")
    private Long ancientBookId;
    private String ancientBookName;

    /** 章节ID */
    @Excel(name = "章节ID")
    private Long chapterId;
    private String chapterName;

    /** 书签创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    @Excel(name = "书签创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdAt;

    /** 坐标 */
    @Excel(name = "坐标")
    private String yAddress;

    public void setBookmarkId(Long bookmarkId) 
    {
        this.bookmarkId = bookmarkId;
    }

    public Long getBookmarkId() 
    {
        return bookmarkId;
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
    public void setCreatedAt(Date createdAt) 
    {
        this.createdAt = createdAt;
    }

    public Date getCreatedAt() 
    {
        return createdAt;
    }
    public void setyAddress(String yAddress) 
    {
        this.yAddress = yAddress;
    }

    public String getyAddress() 
    {
        return yAddress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("bookmarkId", getBookmarkId())
            .append("userId", getUserId())
            .append("ancientBookId", getAncientBookId())
            .append("chapterId", getChapterId())
            .append("createdAt", getCreatedAt())
            .append("yAddress", getyAddress())
            .toString();
    }
}
