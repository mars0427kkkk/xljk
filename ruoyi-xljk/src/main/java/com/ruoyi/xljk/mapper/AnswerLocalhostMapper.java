package com.ruoyi.xljk.mapper;

import java.util.List;
import com.ruoyi.xljk.domain.AnswerLocalhost;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-22
 */
public interface AnswerLocalhostMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public AnswerLocalhost selectAnswerLocalhostById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param answerLocalhost 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<AnswerLocalhost> selectAnswerLocalhostList(AnswerLocalhost answerLocalhost);

    /**
     * 新增【请填写功能名称】
     * 
     * @param answerLocalhost 【请填写功能名称】
     * @return 结果
     */
    public int insertAnswerLocalhost(AnswerLocalhost answerLocalhost);

    /**
     * 修改【请填写功能名称】
     * 
     * @param answerLocalhost 【请填写功能名称】
     * @return 结果
     */
    public int updateAnswerLocalhost(AnswerLocalhost answerLocalhost);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteAnswerLocalhostById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAnswerLocalhostByIds(Long[] ids);
}
