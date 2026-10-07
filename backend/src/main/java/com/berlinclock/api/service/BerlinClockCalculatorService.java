package com.berlinclock.api.service;

import com.berlinclock.api.model.BerlinClockTimeState;
import com.berlinclock.api.utils.ClockColor;

import java.time.LocalTime;
import java.util.List;

public class BerlinClockCalculatorService {

    public BerlinClockTimeState calculateBerlinClockTime(LocalTime time) {

        ClockColor secondsLamp = calculateSecondsLamp(time);

        int fiveHourLamp = time.getHour() / 5;
        List<ClockColor> fiveHoursLamps = calculateFiveHoursLamps(fiveHourLamp);

        return new BerlinClockTimeState(
                secondsLamp,
                fiveHoursLamps,
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

    private List<ClockColor> calculateFiveHoursLamps(int fiveHourLamp) {

        return List.of(
                fiveHourLamp > 0 ? ClockColor.RED : ClockColor.OFF,
                fiveHourLamp > 1 ? ClockColor.RED : ClockColor.OFF,
                fiveHourLamp > 2 ? ClockColor.RED : ClockColor.OFF,
                fiveHourLamp > 3 ? ClockColor.RED : ClockColor.OFF
        );
    }
}
