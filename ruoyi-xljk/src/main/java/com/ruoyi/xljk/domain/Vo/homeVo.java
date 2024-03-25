package com.ruoyi.xljk.domain.Vo;

import java.util.List;

public class homeVo {
    private String name;
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "homeVo{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
