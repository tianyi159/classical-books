package com.guji.book.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.guji.book.domain.ChapterPage;
import com.guji.book.service.IChapterPageService;
import com.guji.common.utils.poi.ExcelUtil;
import com.guji.common.core.page.TableDataInfo;

/**
 * 章节页面Controller
 * 
 * @author chen
 * @date 2025-01-17
 */
@RestController
@RequestMapping("/book/page")
public class ChapterPageController extends BaseController
{
    @Autowired
    private IChapterPageService chapterPageService;

    /**
     * 查询章节页面列表
     */
    @GetMapping("/list")
    public TableDataInfo list(ChapterPage chapterPage)
    {
        startPage();
        List<ChapterPage> list = chapterPageService.selectChapterPageList(chapterPage);
        return getDataTable(list);
    }


    @GetMapping("/lists")
    public AjaxResult lists(ChapterPage chapterPage)
    {
        List<ChapterPage> list = chapterPageService.selectChapterPageList(chapterPage);
        return AjaxResult.success(list);
    }

    /**
     * 导出章节页面列表
     */
    @Log(title = "章节页面", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChapterPage chapterPage)
    {
        List<ChapterPage> list = chapterPageService.selectChapterPageList(chapterPage);
        ExcelUtil<ChapterPage> util = new ExcelUtil<ChapterPage>(ChapterPage.class);
        util.exportExcel(response, list, "章节页面数据");
    }

    /**
     * 获取章节页面详细信息
     */
    @GetMapping(value = "/{pageId}")
    public AjaxResult getInfo(@PathVariable("pageId") Long pageId)
    {
        return success(chapterPageService.selectChapterPageByPageId(pageId));
    }

    /**
     * 新增章节页面
     */
    @Log(title = "章节页面", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChapterPage chapterPage)
    {
        return toAjax(chapterPageService.insertChapterPage(chapterPage));
    }

    /**
     * 修改章节页面
     */
    @Log(title = "章节页面", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChapterPage chapterPage)
    {
        return toAjax(chapterPageService.updateChapterPage(chapterPage));
    }

    /**
     * 删除章节页面
     */
    @Log(title = "章节页面", businessType = BusinessType.DELETE)
	@DeleteMapping("/{pageIds}")
    public AjaxResult remove(@PathVariable Long[] pageIds)
    {
        return toAjax(chapterPageService.deleteChapterPageByPageIds(pageIds));
    }
}
