package com.berlinclock.api.dto;

import com.berlinclock.api.model.BerlinClockTimeState;
import com.berlinclock.api.utils.ClockColor;

import java.util.List;

public record BerlinClockResponse(
        ClockColor seconds,
        List<ClockColor> fiveHoursLamps,
        List<ClockColor> singleHoursLamps,
        List<ClockColor> fiveMinutesLamps,
        List<ClockColor> singleMinutesLamps
) {

    public static BerlinClockResponse from(
            BerlinClockTimeState berlinClockTimeState
    ) {
        return new BerlinClockResponse(
                berlinClockTimeState.secondsLamp(),
                berlinClockTimeState.fiveHoursLamps(),
                berlinClockTimeState.singleHoursLamps(),
                berlinClockTimeState.fiveMinutesLamps(),
                berlinClockTimeState.singleMinutesLamps()
        );
    }
}
