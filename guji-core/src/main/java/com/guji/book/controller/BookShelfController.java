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
import com.guji.book.domain.BookShelf;
import com.guji.book.service.IBookShelfService;
import com.guji.common.utils.poi.ExcelUtil;
import com.guji.common.core.page.TableDataInfo;

/**
 * 收藏Controller
 * 
 * @author chen
 * @date 2025-01-19
 */
@RestController
@RequestMapping("/book/shelf")
public class BookShelfController extends BaseController
{
    @Autowired
    private IBookShelfService bookShelfService;

    /**
     * 查询收藏列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BookShelf bookShelf)
    {
        startPage();
        List<BookShelf> list = bookShelfService.selectBookShelfList(bookShelf);
        return getDataTable(list);
    }


    @GetMapping("/lists")
    public AjaxResult lists(BookShelf bookShelf)
    {
        Long userId = getUserId();
        bookShelf.setUserId(userId);
        List<BookShelf> list = bookShelfService.selectBookShelfList(bookShelf);
        return AjaxResult.success(list);
    }

    /**
     * 导出收藏列表
     */
    @PostMapping("/export")
    public void export(HttpServletResponse response, BookShelf bookShelf)
    {
        List<BookShelf> list = bookShelfService.selectBookShelfList(bookShelf);
        ExcelUtil<BookShelf> util = new ExcelUtil<BookShelf>(BookShelf.class);
        util.exportExcel(response, list, "收藏数据");
    }

    /**
     * 获取收藏详细信息
     */
    @GetMapping(value = "/{shelfId}")
    public AjaxResult getInfo(@PathVariable("shelfId") Long shelfId)
    {
        return success(bookShelfService.selectBookShelfByShelfId(shelfId));
    }

    /**
     * 新增收藏
     */
    @PostMapping
    public AjaxResult add(@RequestBody BookShelf bookShelf)
    {
        Long userId = getUserId();
        bookShelf.setUserId(userId);
        //收藏之前先检查之前有没有收藏过
        List<BookShelf> list = bookShelfService.selectBookShelfList(bookShelf);
        if(list.size()!=0){
            throw new RuntimeException("已经收藏本书，请勿重复收藏");
        }
        bookShelfService.insertBookShelf(bookShelf);
        Long shelfId = bookShelf.getShelfId();
        return AjaxResult.success(bookShelf);
    }

    /**
     * 修改收藏
     */
    @Log(title = "收藏", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BookShelf bookShelf)
    {
        return toAjax(bookShelfService.updateBookShelf(bookShelf));
    }

    /**
     * 删除收藏
     */
    @Log(title = "收藏", businessType = BusinessType.DELETE)
	@DeleteMapping("/{shelfIds}")
    public AjaxResult remove(@PathVariable Long[] shelfIds)
    {
        return toAjax(bookShelfService.deleteBookShelfByShelfIds(shelfIds));
    }
}
