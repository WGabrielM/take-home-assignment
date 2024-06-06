package com.noom.interview.fullstack.sleep.repository;

import com.noom.interview.fullstack.sleep.entity.SleepEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SleepRepository extends JpaRepository<SleepEntity, Long> {

    @Query("SELECT s FROM SleepEntity s WHERE s.date BETWEEN :startDate AND :endDate")
    List<SleepEntity> findSleepEntitiesWithinPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
