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
import com.ruoyi.xljk.domain.Zjsd;
import com.ruoyi.xljk.service.IZjsdService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
@RestController
@Api(tags = "专家视点管理")
@RequestMapping("/xljk/zjsd")
public class ZjsdController extends BaseController
{
    @Autowired
    private IZjsdService zjsdService;

    /**
     * 查询【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:zjsd:list')")
    @ApiOperation("热点资讯查询")
    @GetMapping("/list")
    public TableDataInfo list(Zjsd zjsd)
    {
//        startPage();
        List<Zjsd> list = zjsdService.selectZjsdList(zjsd);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:zjsd:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Zjsd zjsd)
    {
        List<Zjsd> list = zjsdService.selectZjsdList(zjsd);
        ExcelUtil<Zjsd> util = new ExcelUtil<Zjsd>(Zjsd.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:zjsd:query')")
    @GetMapping(value = "/{title}")
    public AjaxResult getInfo(@PathVariable("title") String title)
    {
        return success(zjsdService.selectZjsdByTitle(title));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:zjsd:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Zjsd zjsd)
    {
        return toAjax(zjsdService.insertZjsd(zjsd));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:zjsd:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Zjsd zjsd)
    {
        return toAjax(zjsdService.updateZjsd(zjsd));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:zjsd:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{titles}")
    public AjaxResult remove(@PathVariable String[] titles)
    {
        return toAjax(zjsdService.deleteZjsdByTitles(titles));
    }
}
