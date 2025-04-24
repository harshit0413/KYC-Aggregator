package com.exception;

import java.util.HashMap;
import java.util.Map;

public final class ErrorMapperUtil {

    private static final Map<String, ErrorMapper> errorMap = new HashMap<>();

    private ErrorMapperUtil() {
        throw new IllegalStateException("Utility class");
    }

    static {
        errorMap.put("CONSENT_NOT_GIVEN", new ErrorMapper(101, "Consent not given by the user"));
        errorMap.put("INVALID_PAN_FORMAT", new ErrorMapper(102, "Invalid PAN format should be in 5-4-1 Format"));
        errorMap.put("INVALID_AADHAAR_FORMAT", new ErrorMapper(103, "Invalid Aadhaar format should be of 12 digits"));
        errorMap.put("PAN_NOT_FOUND", new ErrorMapper(104, "PAN not found"));
        errorMap.put("AADHAAR_NOT_FOUND", new ErrorMapper(105, "Aadhaar not found"));
        errorMap.put("UNKNOWN_ERROR", new ErrorMapper(999, "An unknown error occurred"));
    }

    public static ErrorMapper getError(String errorKey) {
        return errorMap.getOrDefault(errorKey, new ErrorMapper(999, "Unknown error"));
    }
}