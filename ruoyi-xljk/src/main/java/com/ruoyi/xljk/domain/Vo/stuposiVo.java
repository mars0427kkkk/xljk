package com.ruoyi.xljk.domain.Vo;

public class stuposiVo {
    private String name;
    private Long type;
    private Long num;

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

    @Override
    public String toString() {
        return "stuposiVo{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", num=" + num +
                '}';
    }
}
