package com.noom.interview.fullstack.sleep.dto;

import com.noom.interview.fullstack.sleep.enums.SleepStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class SleepResponseDTO {

    private Long id;
    private LocalDate date;
    private LocalDateTime timeBed;
    private LocalDateTime timeWake;
    private SleepStatus status;

    private LocalTime totalTime;
}
