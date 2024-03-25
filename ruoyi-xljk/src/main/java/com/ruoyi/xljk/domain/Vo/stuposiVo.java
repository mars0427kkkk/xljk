package com.ruoyi.xljk.domain.Vo;

public class stuposiVo {
    private String name;
    private Long type;
    private Long num;

    private String answer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
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
                '}';
    }
}
