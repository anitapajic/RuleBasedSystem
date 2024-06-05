package com.ftn.sbnz.model.models.utils;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public class DateRange {
    private LocalDate startDate;
    private LocalDate endDate;

    public DateRange(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    public DateRange(){}

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Boolean isDateInRange(LocalDate localDate) {
        if (localDate == null || startDate == null || endDate == null) {
            return false;
        }
        return !localDate.isBefore(startDate) && !localDate.isAfter(endDate);
    }
}
