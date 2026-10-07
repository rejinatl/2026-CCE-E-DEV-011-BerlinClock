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

        int singleHours = time.getHour() % 5;
        List<ClockColor> singleHoursLamps = calculateSingleHoursLamps(singleHours);

        return new BerlinClockTimeState(
                secondsLamp,
                fiveHoursLamps,
                singleHoursLamps,
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
    private List<ClockColor> calculateSingleHoursLamps(int singleHours) {
        return List.of(
                singleHours > 0 ? ClockColor.RED : ClockColor.OFF,
                singleHours > 1 ? ClockColor.RED : ClockColor.OFF,
                singleHours > 2 ? ClockColor.RED : ClockColor.OFF,
                singleHours > 3 ? ClockColor.RED : ClockColor.OFF
        );
    }

}
