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
import com.guji.book.domain.AncientBook;
import com.guji.book.service.IAncientBookService;
import com.guji.common.utils.poi.ExcelUtil;
import com.guji.common.core.page.TableDataInfo;

/**
 * 古籍Controller
 * 
 * @author chen
 * @date 2025-01-17
 */
@RestController
@RequestMapping("/guji/book")
public class AncientBookController extends BaseController
{
    @Autowired
    private IAncientBookService ancientBookService;

    /**
     * 查询古籍列表
     */

    @GetMapping("/list")
    public TableDataInfo list(AncientBook ancientBook)
    {
        startPage();
        List<AncientBook> list = ancientBookService.selectAncientBookList(ancientBook);
        return getDataTable(list);
    }

    @GetMapping("/lists")
    public AjaxResult lists(AncientBook ancientBook)
    {
        List<AncientBook> list = ancientBookService.selectAncientBookList(ancientBook);
        return AjaxResult.success(list);
    }

    /**
     * 导出古籍列表
     */

    @Log(title = "古籍", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AncientBook ancientBook)
    {
        List<AncientBook> list = ancientBookService.selectAncientBookList(ancientBook);
        ExcelUtil<AncientBook> util = new ExcelUtil<AncientBook>(AncientBook.class);
        util.exportExcel(response, list, "古籍数据");
    }

    /**
     * 获取古籍详细信息
     */

    @GetMapping(value = "/{bookId}")
    public AjaxResult getInfo(@PathVariable("bookId") Long bookId)
    {
        return success(ancientBookService.selectAncientBookByBookId(bookId));
    }

    /**
     * 新增古籍
     */

    @Log(title = "古籍", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AncientBook ancientBook)
    {
        return toAjax(ancientBookService.insertAncientBook(ancientBook));
    }

    /**
     * 修改古籍
     */

    @Log(title = "古籍", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AncientBook ancientBook)
    {
        return toAjax(ancientBookService.updateAncientBook(ancientBook));
    }

    /**
     * 删除古籍
     */

    @Log(title = "古籍", businessType = BusinessType.DELETE)
	@DeleteMapping("/{bookIds}")
    public AjaxResult remove(@PathVariable Long[] bookIds)
    {
        return toAjax(ancientBookService.deleteAncientBookByBookIds(bookIds));
    }
}
