package com.ruoyi.xljk.service;

import java.util.List;
import com.ruoyi.xljk.domain.Zjsd;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2024-04-08
 */
public interface IZjsdService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param title 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Zjsd selectZjsdByTitle(String title);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param zjsd 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Zjsd> selectZjsdList(Zjsd zjsd);

    /**
     * 新增【请填写功能名称】
     * 
     * @param zjsd 【请填写功能名称】
     * @return 结果
     */
    public int insertZjsd(Zjsd zjsd);

    /**
     * 修改【请填写功能名称】
     * 
     * @param zjsd 【请填写功能名称】
     * @return 结果
     */
    public int updateZjsd(Zjsd zjsd);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param titles 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteZjsdByTitles(String[] titles);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param title 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteZjsdByTitle(String title);
}
