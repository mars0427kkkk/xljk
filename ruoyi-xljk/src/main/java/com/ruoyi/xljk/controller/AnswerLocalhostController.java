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
import com.ruoyi.xljk.domain.AnswerLocalhost;
import com.ruoyi.xljk.service.IAnswerLocalhostService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2024-03-22
 */
@RestController
@Api(tags = "测评结果管理")
@RequestMapping("/xljk/localhost")
public class AnswerLocalhostController extends BaseController
{
    @Autowired
    private IAnswerLocalhostService answerLocalhostService;

    /**
     * 查询【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:localhost:list')")
    @ApiOperation(value = "查询测评结果列表")
    @GetMapping("/list")
    public TableDataInfo list(AnswerLocalhost answerLocalhost)
    {
        startPage();
        List<AnswerLocalhost> list = answerLocalhostService.selectAnswerLocalhostList(answerLocalhost);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:localhost:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AnswerLocalhost answerLocalhost)
    {
        List<AnswerLocalhost> list = answerLocalhostService.selectAnswerLocalhostList(answerLocalhost);
        ExcelUtil<AnswerLocalhost> util = new ExcelUtil<AnswerLocalhost>(AnswerLocalhost.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:localhost:query')")
    @ApiOperation(value = "id查询测评结果列表")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(answerLocalhostService.selectAnswerLocalhostById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:localhost:add')")
    @ApiOperation(value = "新增测评结果列表")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AnswerLocalhost answerLocalhost)
    {
        return toAjax(answerLocalhostService.insertAnswerLocalhost(answerLocalhost));
    }

    /**
     * 修改【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:localhost:edit')")
    @ApiOperation(value = "修改测评结果列表")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AnswerLocalhost answerLocalhost)
    {
        return toAjax(answerLocalhostService.updateAnswerLocalhost(answerLocalhost));
    }

    /**
     * 删除【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:localhost:remove')")
    @ApiOperation(value = "删除测评结果列表")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(answerLocalhostService.deleteAnswerLocalhostByIds(ids));
    }
}
