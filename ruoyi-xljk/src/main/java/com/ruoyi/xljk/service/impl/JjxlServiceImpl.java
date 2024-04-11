package com.ruoyi.xljk.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.xljk.mapper.JjxlMapper;
import com.ruoyi.xljk.domain.Jjxl;
import com.ruoyi.xljk.service.IJjxlService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-11
 */
@Service
public class JjxlServiceImpl implements IJjxlService 
{
    @Autowired
    private JjxlMapper jjxlMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Jjxl selectJjxlById(Long id)
    {
        return jjxlMapper.selectJjxlById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param jjxl 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Jjxl> selectJjxlList(Jjxl jjxl)
    {
        return jjxlMapper.selectJjxlList(jjxl);
    }

    @Override
    public String selectJjxl(String name ,String level) {
        return jjxlMapper.selectJjxl(name,level);
    }

    @Override
    public String selectJjxl1(String name, String level) {
        return jjxlMapper.selectJjxl1(name,level);
    }

    @Override
    public String selectJjxl2(String name, String level) {
        return jjxlMapper.selectJjxl2(name,level);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param jjxl 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertJjxl(Jjxl jjxl)
    {
        return jjxlMapper.insertJjxl(jjxl);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param jjxl 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateJjxl(Jjxl jjxl)
    {
        return jjxlMapper.updateJjxl(jjxl);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteJjxlByIds(Long[] ids)
    {
        return jjxlMapper.deleteJjxlByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteJjxlById(Long id)
    {
        return jjxlMapper.deleteJjxlById(id);
    }
}
