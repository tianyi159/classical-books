package com.guji.book.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.guji.common.annotation.Excel;
import com.guji.common.core.domain.BaseEntity;
import sun.dc.pr.PRError;

/**
 * 章节页面对象 chapter_page
 * 
 * @author chen
 * @date 2025-01-17
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChapterPage
{
    private static final long serialVersionUID = 1L;

    /** 页面id */
    private Long pageId;

    /** 章节id */
    @Excel(name = "章节id")
    private Long chapterId;

    /** 内容 */
    @Excel(name = "内容")
    private String content;

    private  String chapterName;
}
