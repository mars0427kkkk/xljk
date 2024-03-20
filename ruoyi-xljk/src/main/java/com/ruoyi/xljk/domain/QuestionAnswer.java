package com.ruoyi.xljk.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 question_answer
 * 
 * @author ruoyi
 * @date 2024-03-20
 */
public class QuestionAnswer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 题目id */
    @Excel(name = "题目id")
    private Long questionId;

    /** 题目内容 */
    @Excel(name = "题目内容")
    private String questionContent;

    /** 回答id
 */
    @Excel(name = "回答id")
    private String answerId;

    /** 回答内容 */
    @Excel(name = "回答内容")
    private String answerContent;

    /** 题目分数
 */
    @Excel(name = "题目分数")
    private Long answerScore;

    /** 题目种类id */
    @Excel(name = "题目种类id")
    private Long questionTypeId;

    /** 题目种类 */
    @Excel(name = "题目种类")
    private String questionType;

    /** 题目解析种类 */
    @Excel(name = "题目解析种类")
    private String type;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setQuestionId(Long questionId) 
    {
        this.questionId = questionId;
    }

    public Long getQuestionId() 
    {
        return questionId;
    }
    public void setQuestionContent(String questionContent) 
    {
        this.questionContent = questionContent;
    }

    public String getQuestionContent() 
    {
        return questionContent;
    }
    public void setAnswerId(String answerId) 
    {
        this.answerId = answerId;
    }

    public String getAnswerId() 
    {
        return answerId;
    }
    public void setAnswerContent(String answerContent) 
    {
        this.answerContent = answerContent;
    }

    public String getAnswerContent() 
    {
        return answerContent;
    }
    public void setAnswerScore(Long answerScore) 
    {
        this.answerScore = answerScore;
    }

    public Long getAnswerScore() 
    {
        return answerScore;
    }
    public void setQuestionTypeId(Long questionTypeId) 
    {
        this.questionTypeId = questionTypeId;
    }

    public Long getQuestionTypeId() 
    {
        return questionTypeId;
    }
    public void setQuestionType(String questionType) 
    {
        this.questionType = questionType;
    }

    public String getQuestionType() 
    {
        return questionType;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("questionId", getQuestionId())
            .append("questionContent", getQuestionContent())
            .append("answerId", getAnswerId())
            .append("answerContent", getAnswerContent())
            .append("answerScore", getAnswerScore())
            .append("questionTypeId", getQuestionTypeId())
            .append("questionType", getQuestionType())
            .append("type", getType())
            .toString();
    }
}
