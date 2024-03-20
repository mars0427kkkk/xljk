package com.ruoyi.xljk.service;

import java.util.List;
import com.ruoyi.xljk.domain.QuestionAnswer;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2024-03-20
 */
public interface IQuestionAnswerService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public QuestionAnswer selectQuestionAnswerById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param questionAnswer 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<QuestionAnswer> selectQuestionAnswerList(QuestionAnswer questionAnswer);

    /**
     * 新增【请填写功能名称】
     * 
     * @param questionAnswer 【请填写功能名称】
     * @return 结果
     */
    public int insertQuestionAnswer(QuestionAnswer questionAnswer);

    /**
     * 修改【请填写功能名称】
     * 
     * @param questionAnswer 【请填写功能名称】
     * @return 结果
     */
    public int updateQuestionAnswer(QuestionAnswer questionAnswer);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteQuestionAnswerByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteQuestionAnswerById(Long id);

    List<QuestionAnswer> selectQuestionAnswerNameList();
}
