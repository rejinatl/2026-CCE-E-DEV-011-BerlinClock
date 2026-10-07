package com.berlinclock.api.controller;

import com.berlinclock.api.model.BerlinClockTimeState;
import com.berlinclock.api.service.BerlinClockCalculatorService;
import com.berlinclock.api.utils.ClockColor;
import com.berlinclock.api.utils.CurrentLocalTimeProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = BerlinClockController.class)
class BerlinClockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BerlinClockCalculatorService berlinClockCalculatorService;

    @MockitoBean
    private CurrentLocalTimeProvider currentLocalTimeProvider;

    @Test
    void should_return_current_time_berlin_clock() throws Exception {

        var expectedBerlinClockTimeState = new BerlinClockTimeState(
                ClockColor.YELLOW,
                List.of(ClockColor.RED, ClockColor.RED, ClockColor.OFF, ClockColor.OFF),
                List.of(ClockColor.RED, ClockColor.RED, ClockColor.RED, ClockColor.OFF),
                List.of(ClockColor.YELLOW, ClockColor.YELLOW, ClockColor.RED, ClockColor.YELLOW, ClockColor.YELLOW,
                        ClockColor.OFF, ClockColor.OFF, ClockColor.OFF, ClockColor.OFF, ClockColor.OFF, ClockColor.OFF),
                List.of(ClockColor.YELLOW, ClockColor.YELLOW, ClockColor.OFF, ClockColor.OFF)
        );

        when(currentLocalTimeProvider.now())
                .thenReturn(
                        LocalTime.of(13, 27, 42)
                );
        when(berlinClockCalculatorService.calculateBerlinClockTime(any(LocalTime.class))).thenReturn(
                expectedBerlinClockTimeState
        );

        mockMvc.perform(get("/api/berlin-clock")
                        .header("X-API-Version", "1.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.seconds").value("YELLOW"))
                .andExpect(jsonPath("$.fiveHoursLamps[0]").value("RED"))
                .andExpect(jsonPath("$.fiveHoursLamps[1]").value("RED"))
                .andExpect(jsonPath("$.fiveHoursLamps[2]").value("OFF"))

                .andExpect(jsonPath("$.singleHoursLamps[0]").value("RED"))
                .andExpect(jsonPath("$.singleHoursLamps[1]").value("RED"))
                .andExpect(jsonPath("$.singleHoursLamps[2]").value("RED"))
                .andExpect(jsonPath("$.singleHoursLamps[3]").value("OFF"))

                .andExpect(jsonPath("$.fiveMinutesLamps[0]").value("YELLOW"))
                .andExpect(jsonPath("$.fiveMinutesLamps[1]").value("YELLOW"))
                .andExpect(jsonPath("$.fiveMinutesLamps[2]").value("RED"))

                .andExpect(jsonPath("$.singleMinutesLamps[0]").value("YELLOW"))
                .andExpect(jsonPath("$.singleMinutesLamps[1]").value("YELLOW"));

        verify(berlinClockCalculatorService).calculateBerlinClockTime(any(LocalTime.class));
    }
}
