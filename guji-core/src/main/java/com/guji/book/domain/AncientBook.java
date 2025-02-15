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
 * 古籍对象 ancient_book
 * 
 * @author chen
 * @date 2025-01-17
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AncientBook extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 书籍id */
    private Long bookId;

    /** 类型 */
    @Excel(name = "类型")
    private String type;

    /** 作者 */
    @Excel(name = "作者")
    private String author;

    private String name;

    private String url;

    private String introduce;
}
