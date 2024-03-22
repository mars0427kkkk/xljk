package com.ruoyi.xljk.service.impl;

import java.util.List;
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
}
