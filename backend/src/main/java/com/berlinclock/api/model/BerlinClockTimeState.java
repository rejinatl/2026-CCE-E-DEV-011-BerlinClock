package com.berlinclock.api.model;

import com.berlinclock.api.utils.ClockColor;

import java.util.List;

public record BerlinClockTimeState(
    ClockColor secondsLamp,
    List<ClockColor> fiveHoursLamps,
    List<ClockColor> singleHoursLamps,
    List<ClockColor> fiveMinutesLamps,
    List<ClockColor> singleMinutesLamps
) {}
