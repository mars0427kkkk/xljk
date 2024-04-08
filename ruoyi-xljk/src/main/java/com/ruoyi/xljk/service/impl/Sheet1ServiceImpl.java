package com.ruoyi.xljk.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.xljk.mapper.Sheet1Mapper;
import com.ruoyi.xljk.domain.Sheet1;
import com.ruoyi.xljk.service.ISheet1Service;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
@Service
public class Sheet1ServiceImpl implements ISheet1Service 
{
    @Autowired
    private Sheet1Mapper sheet1Mapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param title 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Sheet1 selectSheet1ByTitle(String title)
    {
        return sheet1Mapper.selectSheet1ByTitle(title);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sheet1 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Sheet1> selectSheet1List(Sheet1 sheet1)
    {
        return sheet1Mapper.selectSheet1List(sheet1);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param sheet1 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertSheet1(Sheet1 sheet1)
    {
        return sheet1Mapper.insertSheet1(sheet1);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param sheet1 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateSheet1(Sheet1 sheet1)
    {
        return sheet1Mapper.updateSheet1(sheet1);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param titles 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSheet1ByTitles(String[] titles)
    {
        return sheet1Mapper.deleteSheet1ByTitles(titles);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param title 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteSheet1ByTitle(String title)
    {
        return sheet1Mapper.deleteSheet1ByTitle(title);
    }
}
