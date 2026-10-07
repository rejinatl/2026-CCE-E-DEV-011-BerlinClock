package com.berlinclock.api.controller;

import com.berlinclock.api.dto.BerlinClockResponse;
import com.berlinclock.api.service.BerlinClockCalculatorService;
import com.berlinclock.api.utils.CurrentLocalTimeProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/berlin-clock")
public class BerlinClockController {

    private final BerlinClockCalculatorService berlinClockCalculatorService;

    private final CurrentLocalTimeProvider currentLocalTimeProvider;

    public BerlinClockController(BerlinClockCalculatorService berlinClockCalculatorService,
                                 CurrentLocalTimeProvider currentLocalTimeProvider) {
        this.berlinClockCalculatorService = berlinClockCalculatorService;
        this.currentLocalTimeProvider = currentLocalTimeProvider;
    }

    @GetMapping(version = "1.0")
    public ResponseEntity<BerlinClockResponse> currentClock() {

        var clock = berlinClockCalculatorService.calculateBerlinClockTime(currentLocalTimeProvider.now());
        return ResponseEntity.ok(
                BerlinClockResponse.from(clock)
        );
    }
}
