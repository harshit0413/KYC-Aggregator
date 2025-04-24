package com.controller;

import com.dto.*;
import com.service.KYCService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kyc")
public class KYCController {

    private final KYCService kycService;

    public KYCController(KYCService kycService) {
        this.kycService = kycService;
    }

    @PostMapping("/verify-pan")
    public ResponseEntity<ResponseWrapper<PANResponse>> verifyPan(@RequestBody PANRequest request) throws Exception {
        PANResponse response = kycService.verifyPan(request);
        if (!response.isVerified()) {
            return new ResponseEntity<>(new ResponseWrapper<>("PAN not found", response), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseWrapper<>("PAN verified successfully", response), HttpStatus.OK);
    }

    @PostMapping("/verify-aadhaar")
    public ResponseEntity<ResponseWrapper<AadhaarResponse>> verifyAadhaar(@RequestBody AadhaarRequest request) throws Exception {
        AadhaarResponse response = kycService.verifyAadhaar(request);
        if (!response.isVerified()) {
            return new ResponseEntity<>(new ResponseWrapper<>("Aadhaar not found", response), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ResponseWrapper<>("Aadhaar verified successfully", response), HttpStatus.OK);
    }
}