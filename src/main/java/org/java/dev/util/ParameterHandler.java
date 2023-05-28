package org.java.dev.util;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ParameterHandler {
    private static final String PATTERN_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static String formatInputTimezone(String inputTimezone) {
        return inputTimezone.replaceFirst(" ", "+");
    }

    public static boolean isValidTimezone(String timezone) {
        boolean result = true;
        String formattedTimezone = formatInputTimezone(timezone);
        try {
            DateTimeFormatter
                    .ofPattern(PATTERN_FORMAT)
                    .withZone(ZoneId.of(formattedTimezone));
        } catch (Exception e) {
            result = false;
        }
        return result;
    }
}

