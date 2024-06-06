package com.noom.interview.fullstack.sleep.services;

import com.noom.interview.fullstack.sleep.dto.SleepAverageDTO;
import com.noom.interview.fullstack.sleep.dto.SleepResponseDTO;
import com.noom.interview.fullstack.sleep.entity.SleepEntity;
import com.noom.interview.fullstack.sleep.enums.SleepStatus;
import com.noom.interview.fullstack.sleep.repository.SleepRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service
public class SleepService {

    private final SleepRepository sleepRepository;
    private final ModelMapper modelMapper;

    public SleepService(SleepRepository sleepRepository, ModelMapper modelMapper) {
        this.sleepRepository = sleepRepository;
        this.modelMapper = modelMapper;
    }

    public SleepEntity saveSleep(SleepEntity sleepEntity) {
        return sleepRepository.save(sleepEntity);
    }

    public List<SleepResponseDTO> listAll() {
        return sleepRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public SleepAverageDTO getSleepAverage() {
        var result = new SleepAverageDTO();
        var end = LocalDate.now();
        var init = end.minusDays(30);

        var lastRecords = sleepRepository.findSleepEntitiesWithinPeriod(init, end);

        if (lastRecords.isEmpty()) {
            throw new IllegalStateException("No sleep records found for the given period.");
        }

        result.setInitPeriod(lastRecords.get(0).getDate());
        result.setEndPeriod(lastRecords.get(lastRecords.size() - 1).getDate());

        fillAverageTimes(lastRecords.stream().map(SleepEntity::getTimeBed).map(LocalDateTime::toLocalTime).toList(), result::setTimeSleepAverage);
        fillAverageTimes(lastRecords.stream().map(SleepEntity::getTimeWake).map(LocalDateTime::toLocalTime).toList(), result::setTimeWakeAverage);

        Duration totalSeconds = Duration.between(result.getTimeSleepAverage(), result.getTimeWakeAverage());
        if (totalSeconds.isNegative()) {
            totalSeconds = totalSeconds.plusDays(1);
        }
        LocalTime resultTime = LocalTime.ofSecondOfDay(totalSeconds.getSeconds()).plusNanos(totalSeconds.getNano());
        result.setSleepHourAverage(resultTime);

        result.setCountBad(countStatus(lastRecords, SleepStatus.BAD));
        result.setCountOk(countStatus(lastRecords, SleepStatus.OK));
        result.setCountGood(countStatus(lastRecords, SleepStatus.GOOD));

        return result;
    }

    private SleepResponseDTO convertToDto(SleepEntity sleepEntity) {
        var sleepDto = modelMapper.map(sleepEntity, SleepResponseDTO.class);

        Duration totalSeconds = Duration.between(sleepDto.getTimeBed(), sleepDto.getTimeWake());
        if (totalSeconds.isNegative()) {
            totalSeconds = totalSeconds.plusDays(1);
        }
        LocalTime resultTime = LocalTime.ofSecondOfDay(totalSeconds.getSeconds()).plusNanos(totalSeconds.getNano());

        sleepDto.setTotalTime(resultTime);
        return sleepDto;
    }

    private void fillAverageTimes(List<LocalTime> times, Consumer<LocalTime> setter) {
        int totalMinutes = times.stream().mapToInt(LocalTime::toSecondOfDay).sum();
        int averageMinutes = totalMinutes / times.size();
        LocalTime averageTime = LocalTime.ofSecondOfDay(averageMinutes);
        setter.accept(averageTime);
    }

    private long countStatus(List<SleepEntity> records, SleepStatus status) {
        return records.stream()
                .map(SleepEntity::getStatus)
                .filter(status::equals)
                .count();
    }
}