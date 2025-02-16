package com.guji.book.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.guji.common.utils.SecurityUtils;
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
import com.guji.book.domain.AncientBookRecord;
import com.guji.book.service.IAncientBookRecordService;
import com.guji.common.utils.poi.ExcelUtil;
import com.guji.common.core.page.TableDataInfo;

/**
 * 阅读记录Controller
 * 
 * @author ruoyi
 * @date 2025-02-16
 */
@RestController
@RequestMapping("/guji/record")
public class AncientBookRecordController extends BaseController
{
    @Autowired
    private IAncientBookRecordService ancientBookRecordService;

    /**
     * 查询阅读记录列表
     */
    @PreAuthorize("@ss.hasPermi('guji:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(AncientBookRecord ancientBookRecord)
    {
        startPage();
        List<AncientBookRecord> list = ancientBookRecordService.selectAncientBookRecordList(ancientBookRecord);
        return getDataTable(list);
    }

    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(ancientBookRecordService.selectAncientBookRecordByRecordId(recordId));
    }

//    添加阅读记录，如果有了，就更新
    @PostMapping(value = "/views")
    public AjaxResult insRecord(@RequestBody AncientBookRecord record)
    {
        Boolean b = ancientBookRecordService.insertByBookRecordOnUpdate(record);
        if(b)
            return success();
        else
            return error("参数错误");
    }

    /**
     * 导出阅读记录列表
     */
    @PreAuthorize("@ss.hasPermi('guji:record:export')")
    @Log(title = "阅读记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AncientBookRecord ancientBookRecord)
    {
        List<AncientBookRecord> list = ancientBookRecordService.selectAncientBookRecordList(ancientBookRecord);
        ExcelUtil<AncientBookRecord> util = new ExcelUtil<AncientBookRecord>(AncientBookRecord.class);
        util.exportExcel(response, list, "阅读记录数据");
    }

    /**
     * 获取这本书，本人的阅读记录
     */
    @GetMapping
    public AjaxResult getRecordInfo(Long bookId)
    {
        return success(ancientBookRecordService.selectAncientBookBookmarksByUserIdAndAncientBookId(SecurityUtils.getUserId(),bookId));
    }

    /**
     * 新增阅读记录
     */
    @PreAuthorize("@ss.hasPermi('guji:record:add')")
    @Log(title = "阅读记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AncientBookRecord ancientBookRecord)
    {
        return toAjax(ancientBookRecordService.insertAncientBookRecord(ancientBookRecord));
    }

    /**
     * 修改阅读记录
     */
    @PreAuthorize("@ss.hasPermi('guji:record:edit')")
    @Log(title = "阅读记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AncientBookRecord ancientBookRecord)
    {
        return toAjax(ancientBookRecordService.updateAncientBookRecord(ancientBookRecord));
    }

    /**
     * 删除阅读记录
     */
    @PreAuthorize("@ss.hasPermi('guji:record:remove')")
    @Log(title = "阅读记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(ancientBookRecordService.deleteAncientBookRecordByRecordIds(recordIds));
    }
}
