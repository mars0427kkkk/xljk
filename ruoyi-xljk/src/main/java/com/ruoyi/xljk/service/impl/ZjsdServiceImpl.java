package com.ruoyi.xljk.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.xljk.mapper.ZjsdMapper;
import com.ruoyi.xljk.domain.Zjsd;
import com.ruoyi.xljk.service.IZjsdService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
@Service
public class ZjsdServiceImpl implements IZjsdService 
{
    @Autowired
    private ZjsdMapper zjsdMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param title 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Zjsd selectZjsdByTitle(String title)
    {
        return zjsdMapper.selectZjsdByTitle(title);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param zjsd 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Zjsd> selectZjsdList(Zjsd zjsd)
    {
        return zjsdMapper.selectZjsdList(zjsd);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param zjsd 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertZjsd(Zjsd zjsd)
    {
        return zjsdMapper.insertZjsd(zjsd);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param zjsd 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateZjsd(Zjsd zjsd)
    {
        return zjsdMapper.updateZjsd(zjsd);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param titles 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteZjsdByTitles(String[] titles)
    {
        return zjsdMapper.deleteZjsdByTitles(titles);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param title 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteZjsdByTitle(String title)
    {
        return zjsdMapper.deleteZjsdByTitle(title);
    }
}
