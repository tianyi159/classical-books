package com.guji.book.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.guji.common.utils.DateUtils;
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
import com.guji.book.domain.AncientPoem;
import com.guji.book.service.IAncientPoemService;
import com.guji.common.utils.poi.ExcelUtil;
import com.guji.common.core.page.TableDataInfo;

/**
 * 古诗Controller
 * 
 * @author chen
 * @date 2025-01-17
 */
@RestController
@RequestMapping("/book/poem")
public class AncientPoemController extends BaseController
{
    @Autowired
    private IAncientPoemService ancientPoemService;

    /**
     * 查询古诗列表
     */
    @GetMapping("/list")
    public TableDataInfo list(AncientPoem ancientPoem)
    {
        startPage();
        List<AncientPoem> list = ancientPoemService.selectAncientPoemList(ancientPoem);
        return getDataTable(list);
    }




    @GetMapping("/lists")
    public AjaxResult lists(AncientPoem ancientPoem)
    {
        List<AncientPoem> list = ancientPoemService.selectAncientPoemList(ancientPoem);
        return AjaxResult.success(list);
    }


    /**
     * 导出古诗列表
     */
    @Log(title = "古诗", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AncientPoem ancientPoem)
    {
        List<AncientPoem> list = ancientPoemService.selectAncientPoemList(ancientPoem);
        ExcelUtil<AncientPoem> util = new ExcelUtil<AncientPoem>(AncientPoem.class);
        util.exportExcel(response, list, "古诗数据");
    }

    /**
     * 获取古诗详细信息
     */
    @GetMapping(value = "/{ariId}")
    public AjaxResult getInfo(@PathVariable("ariId") Long ariId)
    {
        return success(ancientPoemService.selectAncientPoemByAriId(ariId));
    }

    /**
     * 新增古诗
     */
    @Log(title = "古诗", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AncientPoem ancientPoem)
    {
        return toAjax(ancientPoemService.insertAncientPoem(ancientPoem));
    }

    /**
     * 修改古诗
     */
    @Log(title = "古诗", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AncientPoem ancientPoem)
    {
        return toAjax(ancientPoemService.updateAncientPoem(ancientPoem));
    }

    /**
     * 删除古诗
     */
    @Log(title = "古诗", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ariIds}")
    public AjaxResult remove(@PathVariable Long[] ariIds)
    {
        return toAjax(ancientPoemService.deleteAncientPoemByAriIds(ariIds));
    }
}
