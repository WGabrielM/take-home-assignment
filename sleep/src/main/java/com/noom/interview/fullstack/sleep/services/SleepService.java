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

    @Autowired
    private SleepRepository sleepRepository;

    ModelMapper modelMapper = new ModelMapper();

    public SleepEntity saveSleep(SleepEntity sleepEntity) {
        return this.sleepRepository.save(sleepEntity);
    }

    public List<SleepResponseDTO> listAll() {
        var sleepList = sleepRepository.findAll();
        return sleepList.stream().map(sleepEntity -> {

            var sleepDto = modelMapper.map(sleepEntity, SleepResponseDTO.class);

            Duration totalSeconds = Duration.between(sleepDto.getTimeBed(), sleepDto.getTimeWake());

            if (totalSeconds.isNegative()) {
                totalSeconds = totalSeconds.plusDays(1);
            }
            long secondsOfDay = totalSeconds.getSeconds();
            int nanosOfDay = totalSeconds.getNano();
            LocalTime resultTime = LocalTime.ofSecondOfDay(secondsOfDay).plusNanos(nanosOfDay);

            sleepDto.setTotalTime(resultTime);

            return sleepDto;
        }).collect(Collectors.toList());
    }

    public SleepAverageDTO getSleepAvarage() {
        var result = new SleepAverageDTO();

        var end = LocalDate.now();
        var init = end.minusDays(30);

        var lastRecords = sleepRepository.takeThePeriod(init, end);

        result.setInitPeriod(lastRecords.get(0).getDate());
        result.setEndPeriod(lastRecords.get(lastRecords.size() - 1).getDate());

        preencherMediaHoras(lastRecords.stream().map(SleepEntity::getTimeBed).map(LocalDateTime::toLocalTime).toList(), result::setTimeSleepAverage);
        preencherMediaHoras(lastRecords.stream().map(SleepEntity::getTimeWake).map(LocalDateTime::toLocalTime).toList(), result::setTimeWakeAverage);

        Duration totalSeconds = Duration.between(result.getTimeSleepAverage(), result.getTimeWakeAverage());
        if (totalSeconds.isNegative()) {
            totalSeconds = totalSeconds.plusDays(1);
        }
        long secondsOfDay = totalSeconds.getSeconds();
        int nanosOfDay = totalSeconds.getNano();
        LocalTime resultTime = LocalTime.ofSecondOfDay(secondsOfDay).plusNanos(nanosOfDay);
        result.setSleepHourAverage(resultTime);

        result.setCountBad(lastRecords.stream().map(SleepEntity::getStatus).filter(SleepStatus.BAD::equals).count());
        result.setCountOk(lastRecords.stream().map(SleepEntity::getStatus).filter(SleepStatus.OK::equals).count());
        result.setCountGood(lastRecords.stream().map(SleepEntity::getStatus).filter(SleepStatus.GOOD::equals).count());

        return result;
    }

    private static void preencherMediaHoras(List<LocalTime> sleepHours, Consumer<LocalTime> setter) {
        int totalTimeInMinutesSleep = sleepHours.stream().map(LocalTime::getMinute).reduce(0, Integer::sum);
        totalTimeInMinutesSleep += (60 * sleepHours.stream().map(LocalTime::getHour).reduce(0, Integer::sum));
        setter.accept(LocalTime.of(((totalTimeInMinutesSleep / 60) / sleepHours.size()) % 24, (totalTimeInMinutesSleep / sleepHours.size()) % 60));
    }
}
