package com.ftn.sbnz.model.dto.queries;


public class Query1DTO {
    private Integer numOfPatients;
    private Double percentage;

    public Query1DTO(Integer numOfPatients, Double percentage) {
        this.numOfPatients = numOfPatients;
        this.percentage = percentage;
    }

    public Query1DTO(){}

    public Integer getNumOfPatients() {
        return numOfPatients;
    }

    public void setNumOfPatients(Integer numOfPatients) {
        this.numOfPatients = numOfPatients;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
