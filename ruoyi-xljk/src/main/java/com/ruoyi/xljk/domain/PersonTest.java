package com.ruoyi.xljk.domain;
import com.ruoyi.xljk.domain.Vo.stuposiVo;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;
import java.util.List;
@Data
public class PersonTest {
    private String name;
    private int age;
    private String gender;
    private String sportAge;
    private String special;
    private String cityTeam;
    private String testName;
    private Date testTime;
    private String answer;
    private String answerNum;
}
