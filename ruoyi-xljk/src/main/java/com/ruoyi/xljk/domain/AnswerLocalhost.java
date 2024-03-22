package com.ruoyi.xljk.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 answer_localhost
 * 
 * @author ruoyi
 * @date 2024-03-22
 */
public class AnswerLocalhost extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 测试名称 */
    @Excel(name = "测试名称")
    private String testName;

    /** 测试时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "测试时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date testTime;

    /** 用户id
 */
    @Excel(name = "用户id ")
    private String openid;

    /** 测试结果 */
    @Excel(name = "测试结果")
    private String answer;

    /** 测试选项数量
 */
    @Excel(name = "测试选项数量")
    private String answerNum;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTestName(String testName) 
    {
        this.testName = testName;
    }

    public String getTestName() 
    {
        return testName;
    }
    public void setTestTime(Date testTime) 
    {
        this.testTime = testTime;
    }

    public Date getTestTime() 
    {
        return testTime;
    }
    public void setOpenid(String openid) 
    {
        this.openid = openid;
    }

    public String getOpenid() 
    {
        return openid;
    }
    public void setAnswer(String answer) 
    {
        this.answer = answer;
    }

    public String getAnswer() 
    {
        return answer;
    }
    public void setAnswerNum(String answerNum) 
    {
        this.answerNum = answerNum;
    }

    public String getAnswerNum() 
    {
        return answerNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("testName", getTestName())
            .append("testTime", getTestTime())
            .append("openid", getOpenid())
            .append("answer", getAnswer())
            .append("answerNum", getAnswerNum())
            .toString();
    }
}
