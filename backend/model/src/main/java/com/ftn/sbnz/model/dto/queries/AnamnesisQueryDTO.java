package com.ftn.sbnz.model.dto.queries;


import java.time.LocalDateTime;

public class AnamnesisQueryDTO {
    private String patientEmail;
    private LocalDateTime dateTime;

    public AnamnesisQueryDTO(){}

    public AnamnesisQueryDTO(String patientEmail, LocalDateTime dateTime) {
        this.patientEmail = patientEmail;
        this.dateTime = dateTime;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
