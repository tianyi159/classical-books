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
 * 古籍章节对象 book_chapter
 * 
 * @author chen
 * @date 2025-01-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookChapter
{
    private static final long serialVersionUID = 1L;

    /** 图书章节id */
    private Long chapterId;

    /** 古籍id */
    private Long bookId;

    /** 章节名称 */
    @Excel(name = "章节名称")
    private String chapterName;

    private String name;
}
