package com.ruoyi.xljk.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.xljk.domain.QuestionAnswer;
import com.ruoyi.xljk.mapper.QuestionAnswerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.xljk.mapper.AnswerLocalhostMapper;
import com.ruoyi.xljk.domain.AnswerLocalhost;
import com.ruoyi.xljk.service.IAnswerLocalhostService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-03-22
 */
@Service
public class AnswerLocalhostServiceImpl implements IAnswerLocalhostService 
{
    @Autowired
    private AnswerLocalhostMapper answerLocalhostMapper;

    @Override
    public AnswerLocalhost findByOpenid(String openId) {
        return answerLocalhostMapper.selectAnswerLocalhostByOpenId(openId);
    }

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public AnswerLocalhost selectAnswerLocalhostById(Long id)
    {
        return answerLocalhostMapper.selectAnswerLocalhostById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param answerLocalhost 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<AnswerLocalhost> selectAnswerLocalhostList(AnswerLocalhost answerLocalhost)
    {
        return answerLocalhostMapper.selectAnswerLocalhostList(answerLocalhost);
    }



    /**
     * 新增【请填写功能名称】
     * 
     * @param answerLocalhost 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertAnswerLocalhost(AnswerLocalhost answerLocalhost)
    {
        return answerLocalhostMapper.insertAnswerLocalhost(answerLocalhost);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param answerLocalhost 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateAnswerLocalhost(AnswerLocalhost answerLocalhost)
    {
        return answerLocalhostMapper.updateAnswerLocalhost(answerLocalhost);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAnswerLocalhostByIds(Long[] ids)
    {
        return answerLocalhostMapper.deleteAnswerLocalhostByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteAnswerLocalhostById(Long id)
    {
        return answerLocalhostMapper.deleteAnswerLocalhostById(id);
    }

    @Autowired
    private QuestionAnswerMapper questionAnswerMapper;
    @Override
    public List<QuestionAnswer>  selectAnswerLocalhostList1(AnswerLocalhost answerLocalhost) {
        List<String> strings = answerLocalhostMapper.selectAnswerLocalhostList1(answerLocalhost);
        List<QuestionAnswer> list = new ArrayList<>();
        for (String s:strings) {
            QuestionAnswer questionAnswer = new QuestionAnswer();
            questionAnswer.setQuestionType(s);
            String filePath =null;
            List<QuestionAnswer> questionAnswers = questionAnswerMapper.selectQuestionAnswerList(questionAnswer);
            for (QuestionAnswer answer : questionAnswers) {
                 filePath = answer.getFilePath();
            }
            questionAnswer.setFilePath(filePath);
            list.add(questionAnswer);
        }
        return  list;
    }
}
