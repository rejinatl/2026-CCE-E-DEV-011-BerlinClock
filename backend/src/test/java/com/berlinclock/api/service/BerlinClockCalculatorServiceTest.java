package com.berlinclock.api.service;

import com.berlinclock.api.model.BerlinClockTimeState;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static com.berlinclock.api.utils.ClockColor.OFF;
import static com.berlinclock.api.utils.ClockColor.YELLOW;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BerlinClockCalculatorServiceTest {

    @Test
    void even_seconds_should_show_yellow_light_on_seconds_lamp() {

        var berlinClockCalculatorService = new BerlinClockCalculatorService();

        BerlinClockTimeState berlinClockTimeState =
                berlinClockCalculatorService.calculateBerlinClockTime(LocalTime.of(13, 27, 42));

        assertEquals(YELLOW, berlinClockTimeState.secondsLamp());
    }

    @Test
    void odd_seconds_should_show_off_light_on_seconds_lamp() {

        var berlinClockCalculatorService = new BerlinClockCalculatorService();

        BerlinClockTimeState berlinClockTimeState =
                berlinClockCalculatorService.calculateBerlinClockTime(LocalTime.of(13, 27, 41));

        assertEquals(OFF, berlinClockTimeState.secondsLamp());
    }
}
