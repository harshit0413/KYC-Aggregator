package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AadhaarResponse {
    private boolean isVerified;
    private String fullName;
    private String dateOfBirth;
    private String aadhaarNumber;
    private String gender;
    private String mobile;
    private String email;
    private String fatherName;
    private String motherName;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String pin;
}