package com.ruoyi.xljk.domain;

public class nameVo {
    private Long questionId;
    private String questionType;
    private String filePath;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "nameVo{" +
                "questionId=" + questionId +
                ", questionType='" + questionType + '\'' +
                ", filePath='" + filePath + '\'' +
                '}';
    }
}
