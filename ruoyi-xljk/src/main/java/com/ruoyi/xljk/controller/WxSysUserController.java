package com.ruoyi.xljk.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.xljk.service.IWxSysUserService;
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
import com.ruoyi.xljk.domain.WxSysUser;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户信息Controller
 * 
 * @author ruoyi
 * @date 2024-03-27
 */
@RestController
@Api(value = "wx用户信息")
@RequestMapping("/xljk/user")
public class WxSysUserController extends BaseController
{
    @Autowired
    private IWxSysUserService sysUserService;

    /**
     * 查询用户信息列表
     */
//    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(WxSysUser sysUser)
    {
        startPage();
        List<WxSysUser> list = sysUserService.selectSysUserList(sysUser);
        return getDataTable(list);
    }

    /**
     * 导出用户信息列表
     */
//    @PreAuthorize("@ss.hasPermi('system:user:export')")
    @Log(title = "用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WxSysUser sysUser)
    {
        List<WxSysUser> list = sysUserService.selectSysUserList(sysUser);
        ExcelUtil<WxSysUser> util = new ExcelUtil<WxSysUser>(WxSysUser.class);
        util.exportExcel(response, list, "用户信息数据");
    }

    /**
     * 获取用户信息详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:user:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(sysUserService.selectSysUserByUserId(userId));
    }

    /**
     * 新增用户信息
     */
//    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WxSysUser sysUser)
    {
        return toAjax(sysUserService.insertSysUser(sysUser));
    }

    /**
     * 修改用户信息
     */
//    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @ApiOperation("修改用户信息")
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PutMapping(value = "/edit")
    public AjaxResult edit(@RequestBody WxSysUser sysUser)
    {
        return toAjax(sysUserService.updateSysUser(sysUser));
    }

    /**
     * 删除用户信息
     */
//    @PreAuthorize("@ss.hasPermi('system:user:remove')")
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(sysUserService.deleteSysUserByUserIds(userIds));
    }
}
