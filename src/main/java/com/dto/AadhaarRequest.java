package com.dto;

import lombok.Data;

@Data
public class AadhaarRequest {
    private String aadhaar;
    private boolean consentGiven;
}