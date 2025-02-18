package com.guji.book.controller;

import java.util.Date;
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
import com.guji.book.domain.AncientBookViews;
import com.guji.book.service.IAncientBookViewsService;
import com.guji.common.utils.poi.ExcelUtil;
import com.guji.common.core.page.TableDataInfo;

/**
 * 古籍浏览Controller
 * 
 * @author ruoyi
 * @date 2025-02-18
 */
@RestController
@RequestMapping("/guji/views")
public class AncientBookViewsController extends BaseController
{
    @Autowired
    private IAncientBookViewsService ancientBookViewsService;

    /**
     * 查询古籍浏览列表
     */
    @PreAuthorize("@ss.hasPermi('guji:views:list')")
    @GetMapping("/list")
    public TableDataInfo list(AncientBookViews ancientBookViews)
    {
        startPage();
        List<AncientBookViews> list = ancientBookViewsService.selectAncientBookViewsList(ancientBookViews);
        return getDataTable(list);
    }

    @GetMapping("/paihang")
    public TableDataInfo paihang()
    {
        List<AncientBookViews> list = ancientBookViewsService.selectAncientBookViewsDesc();
        return getDataTable(list);
    }

//    传输书本id
    @GetMapping("/visit")
    public AjaxResult visit(AncientBookViews ancientBookViews)
    {
        if(ancientBookViews.getAncientBookId()==null){
            return error("参数错误");
        }
        ancientBookViewsService.controllerViews(ancientBookViews);
        return success();
    }
//    访问添加浏览量

    /**
     * 导出古籍浏览列表
     */
    @PreAuthorize("@ss.hasPermi('guji:views:export')")
    @Log(title = "古籍浏览", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AncientBookViews ancientBookViews)
    {
        List<AncientBookViews> list = ancientBookViewsService.selectAncientBookViewsList(ancientBookViews);
        ExcelUtil<AncientBookViews> util = new ExcelUtil<AncientBookViews>(AncientBookViews.class);
        util.exportExcel(response, list, "古籍浏览数据");
    }

    /**
     * 获取古籍浏览详细信息
     */
    @PreAuthorize("@ss.hasPermi('guji:views:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(ancientBookViewsService.selectAncientBookViewsById(id));
    }

    /**
     * 新增古籍浏览
     */
    @PreAuthorize("@ss.hasPermi('guji:views:add')")
    @Log(title = "古籍浏览", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AncientBookViews ancientBookViews)
    {
        return toAjax(ancientBookViewsService.insertAncientBookViews(ancientBookViews));
    }

    /**
     * 修改古籍浏览
     */
    @PreAuthorize("@ss.hasPermi('guji:views:edit')")
    @Log(title = "古籍浏览", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AncientBookViews ancientBookViews)
    {
        return toAjax(ancientBookViewsService.updateAncientBookViews(ancientBookViews));
    }

    /**
     * 删除古籍浏览
     */
    @PreAuthorize("@ss.hasPermi('guji:views:remove')")
    @Log(title = "古籍浏览", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(ancientBookViewsService.deleteAncientBookViewsByIds(ids));
    }
}
