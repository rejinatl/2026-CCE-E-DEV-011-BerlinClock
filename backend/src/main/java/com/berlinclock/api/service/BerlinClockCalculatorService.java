package com.berlinclock.api.service;

import com.berlinclock.api.model.BerlinClockTimeState;
import com.berlinclock.api.utils.ClockColor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class BerlinClockCalculatorService {

    public BerlinClockTimeState calculateBerlinClockTime(LocalTime time) {

        ClockColor secondsLamp = calculateSecondsLamp(time);

        int fiveHourLamp = time.getHour() / 5;
        List<ClockColor> fiveHoursLamps = calculateFiveHoursLamps(fiveHourLamp);

        int singleHours = time.getHour() % 5;
        List<ClockColor> singleHoursLamps = calculateSingleHoursLamps(singleHours);

        int fiveMinuteLamps = time.getMinute() / 5;
        var fiveMinutesLamps = calculateFiveMinutesLamps(fiveMinuteLamps);

        int singleMinutes = time.getMinute() % 5;
        List<ClockColor> singleMinutesLamps = calculateSingleMinutesLamps(singleMinutes);

        return new BerlinClockTimeState(
                secondsLamp,
                fiveHoursLamps,
                singleHoursLamps,
                fiveMinutesLamps,
                singleMinutesLamps
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

    private List<ClockColor> calculateFiveMinutesLamps(int fiveMinuteLamps) {

        var fiveMinuteRow =  new java.util.ArrayList<ClockColor>();

        for (int position = 1; position <= 11; position++) {

            if (position > fiveMinuteLamps) {
                fiveMinuteRow.add(ClockColor.OFF);
            } else if (position % 3 == 0) {
                fiveMinuteRow.add(ClockColor.RED);
            } else {
                fiveMinuteRow.add(ClockColor.YELLOW);
            }
        }
        return fiveMinuteRow;
    }

    private List<ClockColor> calculateSingleMinutesLamps(int singleMinutes) {

        return List.of(
                singleMinutes > 0 ? ClockColor.YELLOW : ClockColor.OFF,
                singleMinutes > 1 ? ClockColor.YELLOW : ClockColor.OFF,
                singleMinutes > 2 ? ClockColor.YELLOW : ClockColor.OFF,
                singleMinutes > 3 ? ClockColor.YELLOW : ClockColor.OFF
        );
    }

}
