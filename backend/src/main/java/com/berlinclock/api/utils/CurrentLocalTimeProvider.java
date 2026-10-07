package com.berlinclock.api.utils;

import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.ZoneId;

@Component
public class CurrentLocalTimeProvider {

    public LocalTime now() {
        return LocalTime.now(ZoneId.systemDefault());
    }
}
