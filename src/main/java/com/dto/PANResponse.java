package com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PANResponse {
    private boolean isVerified;
    private String fullName;
    private String dateOfBirth;
    private String panNumber;
    private String mobile;
    private String fatherName;
    private String motherName;
}