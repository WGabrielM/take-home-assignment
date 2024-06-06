package com.noom.interview.fullstack.sleep.controller;

import com.noom.interview.fullstack.sleep.dto.SleepAverageDTO;
import com.noom.interview.fullstack.sleep.dto.SleepResponseDTO;
import com.noom.interview.fullstack.sleep.entity.SleepEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import com.noom.interview.fullstack.sleep.services.SleepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sleep")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SleepController {

    @Autowired
    private SleepService sleepService;


    /**
     * Creates a new sleep entry.
     *
     * @param sleepEntity the sleep entity to create
     * @return the created sleep entity
     */

    @PostMapping
    public ResponseEntity<Object> createSleep(@RequestBody SleepEntity sleepEntity) {
        try {
            var result = sleepService.saveSleep(sleepEntity);
            return ResponseEntity.ok().body(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    /**
     * Retrieves all sleep entries.
     *
     * @return the list of all sleep entries
     */
    @GetMapping
    public ResponseEntity<List<SleepResponseDTO>> getAll() {
        var result = sleepService.listAll();
        return ResponseEntity.ok(result);
    }

    /**
     * Retrieves the average sleep statistics.
     *
     * @return the average sleep statistics
     */
    @GetMapping("/average")
    public ResponseEntity<SleepAverageDTO> getSleepAverage() {
        var result = sleepService.getSleepAverage();
        return ResponseEntity.ok(result);
    }



}