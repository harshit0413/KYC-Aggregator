package com.utils;

import java.util.regex.Pattern;

public final class ValidationUtil {

    private ValidationUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isValidPan(String pan) {
        String panRegex = "[A-Z]{5}\\d{4}[A-Z]";
        return Pattern.matches(panRegex, pan);
    }

    public static boolean isValidAadhaar(String aadhaar) {
        String aadhaarRegex = "\\d{12}";
        return Pattern.matches(aadhaarRegex, aadhaar);
    }
}