package com.ruoyi.xljk.mapper;

import java.util.List;
import com.ruoyi.xljk.domain.QuestionAnswer;
import org.apache.ibatis.annotations.Param;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2024-03-20
 */
public interface QuestionAnswerMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public QuestionAnswer selectQuestionAnswerById(Long id);
    public int selectQuestionAnswer(@Param("name") Long name, @Param("type") String type);

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
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteQuestionAnswerById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteQuestionAnswerByIds(Long[] ids);

    List<QuestionAnswer> selectQuestionAnswerNameList(String type);
}
