package com.guji.book.controller;

import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import com.guji.book.mapper.BookChapterMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.guji.common.annotation.Log;
import com.guji.common.core.controller.BaseController;
import com.guji.common.core.domain.AjaxResult;
import com.guji.common.enums.BusinessType;
import com.guji.book.domain.BookChapter;
import com.guji.book.service.IBookChapterService;
import com.guji.common.utils.poi.ExcelUtil;
import com.guji.common.core.page.TableDataInfo;

/**
 * 古籍章节Controller
 * 
 * @author chen
 * @date 2025-01-17
 */
@RestController
@RequestMapping("/book/chapter")
public class BookChapterController extends BaseController
{
    @Autowired
    private IBookChapterService bookChapterService;

    @Resource
    private BookChapterMapper bookChapterMapper;
    /**
     * 查询古籍章节列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BookChapter bookChapter)
    {
        startPage();
        List<BookChapter> list = bookChapterService.selectBookChapterList(bookChapter);
        return getDataTable(list);
    }


    @GetMapping("/lists")
    public AjaxResult lists(BookChapter bookChapter){
        List<BookChapter> lists = bookChapterService.selectBookChapterList(bookChapter);
        return AjaxResult.success(lists);
    }

    /**
     * 导出古籍章节列表
     */
    @Log(title = "古籍章节", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, BookChapter bookChapter)
    {
        List<BookChapter> list = bookChapterService.selectBookChapterList(bookChapter);
        ExcelUtil<BookChapter> util = new ExcelUtil<BookChapter>(BookChapter.class);
        util.exportExcel(response, list, "古籍章节数据");
    }

    /**
     * 获取古籍章节详细信息
     */
    @GetMapping(value = "/{chapterId}")
    public AjaxResult getInfo(@PathVariable("chapterId") Long chapterId)
    {
        return success(bookChapterService.selectBookChapterByChapterId(chapterId));
    }

    /**
     * 新增古籍章节
     */
    @Log(title = "古籍章节", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BookChapter bookChapter)
    {
        return toAjax(bookChapterService.insertBookChapter(bookChapter));
    }

    /**
     * 修改古籍章节
     */
    @PreAuthorize("@ss.hasPermi('book:chapter:edit')")
    @Log(title = "古籍章节", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BookChapter bookChapter)
    {
        return toAjax(bookChapterService.updateBookChapter(bookChapter));
    }

    /**
     * 删除古籍章节
     */
    @Log(title = "古籍章节", businessType = BusinessType.DELETE)
	@DeleteMapping("/{chapterIds}")
    public AjaxResult remove(@PathVariable Long[] chapterIds)
    {
        return toAjax(bookChapterService.deleteBookChapterByChapterIds(chapterIds));
    }
}
