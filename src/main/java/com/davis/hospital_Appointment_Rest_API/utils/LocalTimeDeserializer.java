package com.davis.hospital_Appointment_Rest_API.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeDeserializer extends JsonDeserializer<LocalTime> {
    private static final DateTimeFormatter FORMATTER = 
        DateTimeFormatter.ofPattern("hh:mm a");

    @Override
    public LocalTime deserialize(JsonParser p, DeserializationContext ctx) 
        throws IOException {
        String timeStr = p.getText();
        return LocalTime.parse(timeStr.toUpperCase(), FORMATTER);
    }
}