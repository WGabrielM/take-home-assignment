package com.noom.interview.fullstack.sleep.entity;

import com.noom.interview.fullstack.sleep.enums.SleepStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Table(name = "sleep")
@Entity
public class SleepEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time_bed")
    private LocalDateTime timeBed;

    @Column(name = "time_wake")
    private LocalDateTime timeWake;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private SleepStatus status;
}
