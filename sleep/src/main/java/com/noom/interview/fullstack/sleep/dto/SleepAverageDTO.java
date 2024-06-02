package com.noom.interview.fullstack.sleep.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class SleepAverageDTO {
    private LocalDate initPeriod;
    private LocalDate endPeriod;
    private LocalTime sleepHourAverage;
    private LocalTime timeSleepAverage;
    private LocalTime timeWakeAverage;
    private Long countBad;
    private Long countOk;
    private Long countGood;
}
