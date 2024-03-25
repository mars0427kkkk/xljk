package com.ruoyi.xljk.domain.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.xljk.domain.correctionalMode;

import java.util.Date;
import java.util.List;

public class homeanswerVo extends correctionalMode {
    private Long A;
    private Long B;
    private Long C;
    private Long D;
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

    private List<homeVo> answer;

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public List<homeVo> getAnswer() {
        return answer;
    }

    public void setAnswer(List<homeVo> answer) {
        this.answer = answer;
    }

    public Long getA() {
        return A;
    }

    public void setA(Long a) {
        A = a;
    }

    public Long getB() {
        return B;
    }

    public void setB(Long b) {
        B = b;
    }

    public Long getC() {
        return C;
    }

    public void setC(Long c) {
        C = c;
    }

    public Long getD() {
        return D;
    }

    public void setD(Long d) {
        D = d;
    }

    @Override
    public String toString() {
        return "homeanswerVo{" +
                "A=" + A +
                ", B=" + B +
                ", C=" + C +
                ", D=" + D +
                ", testName='" + testName + '\'' +
                ", testTime=" + testTime +
                ", openid='" + openid + '\'' +
                ", answer=" + answer +
                '}';
    }
}
