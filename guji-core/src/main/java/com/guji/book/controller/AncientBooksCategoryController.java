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
import com.guji.book.domain.AncientBooksCategory;
import com.guji.book.service.IAncientBooksCategoryService;
import com.guji.common.utils.poi.ExcelUtil;
import com.guji.common.core.page.TableDataInfo;

/**
 * 古籍类型分类Controller
 * 
 * @author ruoyi
 * @date 2025-02-16
 */
@RestController
@RequestMapping("/guji/category")
public class AncientBooksCategoryController extends BaseController
{
    @Autowired
    private IAncientBooksCategoryService ancientBooksCategoryService;

    /**
     * 查询古籍类型分类列表
     */
    @PreAuthorize("@ss.hasPermi('guji:category:list')")
    @GetMapping("/list")
    public TableDataInfo list(AncientBooksCategory ancientBooksCategory)
    {
        startPage();
        List<AncientBooksCategory> list = ancientBooksCategoryService.selectAncientBooksCategoryList(ancientBooksCategory);
        return getDataTable(list);
    }

    /**
     * 导出古籍类型分类列表
     */
    @PreAuthorize("@ss.hasPermi('guji:category:export')")
    @Log(title = "古籍类型分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AncientBooksCategory ancientBooksCategory)
    {
        List<AncientBooksCategory> list = ancientBooksCategoryService.selectAncientBooksCategoryList(ancientBooksCategory);
        ExcelUtil<AncientBooksCategory> util = new ExcelUtil<AncientBooksCategory>(AncientBooksCategory.class);
        util.exportExcel(response, list, "古籍类型分类数据");
    }

    /**
     * 获取古籍类型分类详细信息
     */
    @PreAuthorize("@ss.hasPermi('guji:category:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId)
    {
        return success(ancientBooksCategoryService.selectAncientBooksCategoryByCategoryId(categoryId));
    }

    /**
     * 新增古籍类型分类
     */
    @PreAuthorize("@ss.hasPermi('guji:category:add')")
    @Log(title = "古籍类型分类", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AncientBooksCategory ancientBooksCategory)
    {
        return toAjax(ancientBooksCategoryService.insertAncientBooksCategory(ancientBooksCategory));
    }

    /**
     * 修改古籍类型分类
     */
    @PreAuthorize("@ss.hasPermi('guji:category:edit')")
    @Log(title = "古籍类型分类", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AncientBooksCategory ancientBooksCategory)
    {
        return toAjax(ancientBooksCategoryService.updateAncientBooksCategory(ancientBooksCategory));
    }

    /**
     * 删除古籍类型分类
     */
    @PreAuthorize("@ss.hasPermi('guji:category:remove')")
    @Log(title = "古籍类型分类", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds)
    {
        return toAjax(ancientBooksCategoryService.deleteAncientBooksCategoryByCategoryIds(categoryIds));
    }
}
