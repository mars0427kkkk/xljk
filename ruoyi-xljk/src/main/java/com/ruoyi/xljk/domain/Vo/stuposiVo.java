package com.ruoyi.xljk.domain.Vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class stuposiVo {
    private String name;
    private Double type;
    private Long num;

    private String answer;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date testTime;

    public Date getTestTime() {
        return testTime;
    }

    public void setTestTime(Date testTime) {
        this.testTime = testTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getType() {
        return type;
    }

    public void setType(Double type) {
        this.type = type;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "stuposiVo{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", num=" + num +
                ", answer='" + answer + '\'' +
                ", testTime=" + testTime +
                '}';
    }
}
