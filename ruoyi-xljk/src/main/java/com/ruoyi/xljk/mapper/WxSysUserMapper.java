package com.ruoyi.xljk.mapper;

import com.ruoyi.xljk.domain.WxSysUser;

import java.util.List;


/**
 * 用户信息Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-27
 */
public interface WxSysUserMapper 
{
    /**
     * 查询用户信息
     * 
     * @param userId 用户信息主键
     * @return 用户信息
     */
    public WxSysUser selectSysUserByUserId(Long userId);

    /**
     * 查询用户信息列表
     * 
     * @param sysUser 用户信息
     * @return 用户信息集合
     */
    public List<WxSysUser> selectSysUserList(WxSysUser sysUser);

    /**
     * 新增用户信息
     * 
     * @param sysUser 用户信息
     * @return 结果
     */
    public int insertSysUser(WxSysUser sysUser);

    /**
     * 修改用户信息
     * 
     * @param sysUser 用户信息
     * @return 结果
     */
    public int updateSysUser(WxSysUser sysUser);

    /**
     * 删除用户信息
     * 
     * @param userId 用户信息主键
     * @return 结果
     */
    public int deleteSysUserByUserId(Long userId);

    /**
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysUserByUserIds(Long[] userIds);

    public WxSysUser selectSysUserByOpenId(String openId);
}
