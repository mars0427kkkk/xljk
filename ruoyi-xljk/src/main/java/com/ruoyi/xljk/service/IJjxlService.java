package com.ruoyi.xljk.service;

import java.util.List;
import com.ruoyi.xljk.domain.Jjxl;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2024-04-11
 */
public interface IJjxlService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public Jjxl selectJjxlById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param jjxl 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<Jjxl> selectJjxlList(Jjxl jjxl);
    public String selectJjxl(String name,String level);
    public String selectJjxl1(String name,String level);
    public String selectJjxl2(String name,String level);

    /**
     * 新增【请填写功能名称】
     * 
     * @param jjxl 【请填写功能名称】
     * @return 结果
     */
    public int insertJjxl(Jjxl jjxl);

    /**
     * 修改【请填写功能名称】
     * 
     * @param jjxl 【请填写功能名称】
     * @return 结果
     */
    public int updateJjxl(Jjxl jjxl);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteJjxlByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteJjxlById(Long id);
}
