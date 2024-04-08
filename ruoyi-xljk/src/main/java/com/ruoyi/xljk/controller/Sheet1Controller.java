package com.ruoyi.xljk.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.xljk.domain.Sheet1;
import com.ruoyi.xljk.service.ISheet1Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
@RestController
@Api(tags = "热点资讯管理")
@RequestMapping("/xljk/Sheet1")
public class Sheet1Controller extends BaseController
{
    @Autowired
    private ISheet1Service sheet1Service;

    /**
     * 查询【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:Sheet1:list')")
    @ApiOperation("热点资讯查询")
    @GetMapping("/list")
    public TableDataInfo list(Sheet1 sheet1)
    {
//        startPage();
        List<Sheet1> list = sheet1Service.selectSheet1List(sheet1);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:Sheet1:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Sheet1 sheet1)
    {
        List<Sheet1> list = sheet1Service.selectSheet1List(sheet1);
        ExcelUtil<Sheet1> util = new ExcelUtil<Sheet1>(Sheet1.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:Sheet1:query')")
    @GetMapping(value = "/{title}")
    public AjaxResult getInfo(@PathVariable("title") String title)
    {
        return success(sheet1Service.selectSheet1ByTitle(title));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:Sheet1:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Sheet1 sheet1)
    {
        return toAjax(sheet1Service.insertSheet1(sheet1));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:Sheet1:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Sheet1 sheet1)
    {
        return toAjax(sheet1Service.updateSheet1(sheet1));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:Sheet1:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{titles}")
    public AjaxResult remove(@PathVariable String[] titles)
    {
        return toAjax(sheet1Service.deleteSheet1ByTitles(titles));
    }
}
