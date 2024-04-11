package com.ruoyi.xljk.mapper;

import java.util.List;
import com.ruoyi.xljk.domain.Jjxl;
import org.apache.ibatis.annotations.Param;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2024-04-11
 */
public interface JjxlMapper 
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
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteJjxlById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteJjxlByIds(Long[] ids);

    public String selectJjxl(@Param("name") String name,@Param("level") String level);
    public String selectJjxl1(@Param("name") String name,@Param("level") String level);
    public String selectJjxl2(@Param("name") String name,@Param("level") String level);
}
