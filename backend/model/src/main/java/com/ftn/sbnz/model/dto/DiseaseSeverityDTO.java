package com.ftn.sbnz.model.dto;

public class DiseaseSeverityDTO {

    private Double threshold1;
    private Double threshold2;
    private Integer severityLevel;

    public DiseaseSeverityDTO(Double threshold1, Double threshold2, Integer severityLevel) {
        this.threshold1 = threshold1;
        this.threshold2 = threshold2;
        this.severityLevel = severityLevel;
    }

    public DiseaseSeverityDTO() {
    }

    public Double getThreshold1() {
        return threshold1;
    }

    public void setThreshold1(Double threshold1) {
        this.threshold1 = threshold1;
    }

    public Double getThreshold2() {
        return threshold2;
    }

    public void setThreshold2(Double threshold2) {
        this.threshold2 = threshold2;
    }

    public Integer getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(Integer severityLevel) {
        this.severityLevel = severityLevel;
    }
}
