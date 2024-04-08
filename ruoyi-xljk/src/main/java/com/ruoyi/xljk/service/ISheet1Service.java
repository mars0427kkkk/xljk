package com.ruoyi.xljk.service;

import java.util.List;
import com.ruoyi.xljk.domain.Sheet1;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
public interface ISheet1Service 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param title 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Sheet1 selectSheet1ByTitle(String title);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param sheet1 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Sheet1> selectSheet1List(Sheet1 sheet1);

    /**
     * 新增【请填写功能名称】
     * 
     * @param sheet1 【请填写功能名称】
     * @return 结果
     */
    public int insertSheet1(Sheet1 sheet1);

    /**
     * 修改【请填写功能名称】
     * 
     * @param sheet1 【请填写功能名称】
     * @return 结果
     */
    public int updateSheet1(Sheet1 sheet1);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param titles 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteSheet1ByTitles(String[] titles);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param title 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteSheet1ByTitle(String title);
}
