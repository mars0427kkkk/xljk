package com.ruoyi.xljk.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.xljk.mapper.QuestionAnswerMapper;
import com.ruoyi.xljk.domain.QuestionAnswer;
import com.ruoyi.xljk.service.IQuestionAnswerService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-20
 */
@Service
public class QuestionAnswerServiceImpl implements IQuestionAnswerService 
{
    @Autowired
    private QuestionAnswerMapper questionAnswerMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public QuestionAnswer selectQuestionAnswerById(Long id)
    {
        return questionAnswerMapper.selectQuestionAnswerById(id);
    }

    @Override
    public int selectQuestionAnswer(String name, String type) {
        return questionAnswerMapper.selectQuestionAnswer(name,type);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param questionAnswer 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<QuestionAnswer> selectQuestionAnswerList(QuestionAnswer questionAnswer)
    {
        return questionAnswerMapper.selectQuestionAnswerList(questionAnswer);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param questionAnswer 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertQuestionAnswer(QuestionAnswer questionAnswer)
    {
        return questionAnswerMapper.insertQuestionAnswer(questionAnswer);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param questionAnswer 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateQuestionAnswer(QuestionAnswer questionAnswer)
    {
        return questionAnswerMapper.updateQuestionAnswer(questionAnswer);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteQuestionAnswerByIds(Long[] ids)
    {
        return questionAnswerMapper.deleteQuestionAnswerByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteQuestionAnswerById(Long id)
    {
        return questionAnswerMapper.deleteQuestionAnswerById(id);
    }

    @Override
    public List<QuestionAnswer> selectQuestionAnswerNameList(String type) {
        return questionAnswerMapper.selectQuestionAnswerNameList(type);
    }
}
