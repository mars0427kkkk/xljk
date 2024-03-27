package com.ruoyi.xljk.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.xljk.domain.WxSysUser;
import com.ruoyi.xljk.mapper.WxSysUserMapper;
import com.ruoyi.xljk.service.IWxSysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * 用户信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-27
 */
@Service
public class WxSysUserServiceImpl implements IWxSysUserService
{
    @Autowired
    private WxSysUserMapper sysUserMapper;

    /**
     * 查询用户信息
     * 
     * @param userId 用户信息主键
     * @return 用户信息
     */
    @Override
    public WxSysUser selectSysUserByUserId(Long userId)
    {
        return sysUserMapper.selectSysUserByUserId(userId);
    }

    @Override
    public WxSysUser selectSysUserByOpenId(String openId) {
        return sysUserMapper.selectSysUserByOpenId(openId);
    }


    /**
     * 查询用户信息列表
     * 
     * @param sysUser 用户信息
     * @return 用户信息
     */
    @Override
    public List<WxSysUser> selectSysUserList(WxSysUser sysUser)
    {
        return sysUserMapper.selectSysUserList(sysUser);
    }

    /**
     * 新增用户信息
     * 
     * @param sysUser 用户信息
     * @return 结果
     */
    @Override
    public int insertSysUser(WxSysUser sysUser)
    {
        sysUser.setCreateTime(DateUtils.getNowDate());
        return sysUserMapper.insertSysUser(sysUser);
    }

    /**
     * 修改用户信息
     * 
     * @param sysUser 用户信息
     * @return 结果
     */
    @Override
    public int updateSysUser(WxSysUser sysUser)
    {
        sysUser.setUpdateTime(DateUtils.getNowDate());
        return sysUserMapper.updateSysUser(sysUser);
    }

    /**
     * 批量删除用户信息
     * 
     * @param userIds 需要删除的用户信息主键
     * @return 结果
     */
    @Override
    public int deleteSysUserByUserIds(Long[] userIds)
    {
        return sysUserMapper.deleteSysUserByUserIds(userIds);
    }

    /**
     * 删除用户信息信息
     * 
     * @param userId 用户信息主键
     * @return 结果
     */
    @Override
    public int deleteSysUserByUserId(Long userId)
    {
        return sysUserMapper.deleteSysUserByUserId(userId);
    }
}
