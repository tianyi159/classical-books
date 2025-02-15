package com.guji.book.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guji.common.annotation.Excel;
import com.guji.common.core.domain.BaseEntity;

/**
 * 收藏对象 book_shelf
 * 
 * @author chen
 * @date 2025-01-19
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookShelf
{
    private static final long serialVersionUID = 1L;

    /** 主键id */
    private Long shelfId;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 文章id */
    @Excel(name = "文章id")
    private Long bookId;

    private String userName;

    private String name;

    private String author;

    private String url;
}
