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
import com.guji.book.domain.AncientBookBookmarks;
import com.guji.book.service.IAncientBookBookmarksService;
import com.guji.common.utils.poi.ExcelUtil;
import com.guji.common.core.page.TableDataInfo;

/**
 * 书签Controller
 * 
 * @author ruoyi
 * @date 2025-02-16
 */
@RestController
@RequestMapping("/guji/bookmarks")
public class AncientBookBookmarksController extends BaseController
{
    @Autowired
    private IAncientBookBookmarksService ancientBookBookmarksService;

    /**
     * 查询书签列表
     */
    @PreAuthorize("@ss.hasPermi('guji:bookmarks:list')")
    @GetMapping("/list")
    public TableDataInfo list(AncientBookBookmarks ancientBookBookmarks)
    {
        startPage();
        List<AncientBookBookmarks> list = ancientBookBookmarksService.selectAncientBookBookmarksList(ancientBookBookmarks);
        return getDataTable(list);
    }

    /**
     * 导出书签列表
     */
    @PreAuthorize("@ss.hasPermi('guji:bookmarks:export')")
    @Log(title = "书签", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AncientBookBookmarks ancientBookBookmarks)
    {
        List<AncientBookBookmarks> list = ancientBookBookmarksService.selectAncientBookBookmarksList(ancientBookBookmarks);
        ExcelUtil<AncientBookBookmarks> util = new ExcelUtil<AncientBookBookmarks>(AncientBookBookmarks.class);
        util.exportExcel(response, list, "书签数据");
    }

    /**
     * 获取书签详细信息
     */
    @PreAuthorize("@ss.hasPermi('guji:bookmarks:query')")
    @GetMapping(value = "/{bookmarkId}")
    public AjaxResult getInfo(@PathVariable("bookmarkId") Long bookmarkId)
    {
        return success(ancientBookBookmarksService.selectAncientBookBookmarksByBookmarkId(bookmarkId));
    }

    /**
     * 新增书签
     */
    @PreAuthorize("@ss.hasPermi('guji:bookmarks:add')")
    @Log(title = "书签", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AncientBookBookmarks ancientBookBookmarks)
    {
        ancientBookBookmarks.setUserId(getUserId());
        return toAjax(ancientBookBookmarksService.insertAncientBookBookmarks(ancientBookBookmarks));
    }

    /**
     * 修改书签
     */
    @PreAuthorize("@ss.hasPermi('guji:bookmarks:edit')")
    @Log(title = "书签", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AncientBookBookmarks ancientBookBookmarks)
    {
        return toAjax(ancientBookBookmarksService.updateAncientBookBookmarks(ancientBookBookmarks));
    }

    /**
     * 删除书签
     */
    @PreAuthorize("@ss.hasPermi('guji:bookmarks:remove')")
    @Log(title = "书签", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bookmarkIds}")
    public AjaxResult remove(@PathVariable Long[] bookmarkIds)
    {
        return toAjax(ancientBookBookmarksService.deleteAncientBookBookmarksByBookmarkIds(bookmarkIds));
    }
}
