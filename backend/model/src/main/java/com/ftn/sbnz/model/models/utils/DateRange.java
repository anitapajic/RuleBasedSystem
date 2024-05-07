package com.ftn.sbnz.model.models.utils;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public class DateRange {
    private LocalDate startDate;
    private LocalDate endDate;
}
