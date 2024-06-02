package com.noom.interview.fullstack.sleep.controller;

import com.noom.interview.fullstack.sleep.dto.SleepAverageDTO;
import com.noom.interview.fullstack.sleep.dto.SleepResponseDTO;
import com.noom.interview.fullstack.sleep.entity.SleepEntity;
import org.springframework.beans.factory.annotation.Autowired;
import com.noom.interview.fullstack.sleep.services.SleepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sleep")
public class SleepController {

    @Autowired
    private SleepService sleepService;

    @PostMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Object> createSleep(@RequestBody SleepEntity sleepEntity) {
        try {
            var result = this.sleepService.saveSleep(sleepEntity);
            return ResponseEntity.ok().body(result);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<List<SleepResponseDTO>> getAll() {
        var result = sleepService.listAll();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/average")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<SleepAverageDTO> getSleepAverage() {
        var result = sleepService.getSleepAvarage();
        return ResponseEntity.ok(result);
    }



}