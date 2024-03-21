package com.ruoyi.xljk.domain;

public class correctionalMode {
    private Long strongControl;
    private Long drowningIndulgence;
    private Long ingnoringIndifference;
    private Long authoritativeDemocracy;

    public Long getStrongControl() {
        return strongControl;
    }


    public void setStrongControl(Long strongControl) {
        this.strongControl = strongControl;
    }

    public Long getDrowningIndulgence() {
        return drowningIndulgence;
    }

    public void setDrowningIndulgence(Long drowningIndulgence) {
        this.drowningIndulgence = drowningIndulgence;
    }

    public Long getIngnoringIndifference() {
        return ingnoringIndifference;
    }

    public void setIngnoringIndifference(Long ingnoringIndifference) {
        this.ingnoringIndifference = ingnoringIndifference;
    }

    public Long getAuthoritativeDemocracy() {
        return authoritativeDemocracy;
    }

    public void setAuthoritativeDemocracy(Long authoritativeDemocracy) {
        this.authoritativeDemocracy = authoritativeDemocracy;
    }

    @Override
    public String toString() {
        return "correctionalMode{" +
                "strongControl=" + strongControl +
                ", drowningIndulgence=" + drowningIndulgence +
                ", ingnoringIndifference=" + ingnoringIndifference +
                ", authoritativeDemocracy=" + authoritativeDemocracy +
                '}';
    }
}
