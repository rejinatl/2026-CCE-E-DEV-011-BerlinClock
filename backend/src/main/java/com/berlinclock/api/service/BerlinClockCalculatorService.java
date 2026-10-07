package com.berlinclock.api.service;

import com.berlinclock.api.model.BerlinClockTimeState;
import com.berlinclock.api.utils.ClockColor;

import java.time.LocalTime;
import java.util.List;

public class BerlinClockCalculatorService {

    public BerlinClockTimeState calculateBerlinClockTime(LocalTime time) {

        ClockColor secondsLamp = calculateSecondsLamp(time);

        return new BerlinClockTimeState(
                secondsLamp,
                List.of(),
                List.of(),
                List.of(),
                List.of()
        );
    }

    private ClockColor calculateSecondsLamp(LocalTime time) {

        if (time.getSecond() % 2 == 0) {
            return ClockColor.YELLOW;
        } else {
            return ClockColor.OFF;
        }
    }
}
