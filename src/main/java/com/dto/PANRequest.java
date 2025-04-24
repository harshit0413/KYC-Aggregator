package com.dto;

import lombok.Data;

@Data
public class PANRequest {
    private String pan;
    private boolean consentGiven;
}