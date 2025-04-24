package com;

import com.dto.AadhaarRequest;
import com.dto.AadhaarResponse;
import com.dto.PANRequest;
import com.dto.PANResponse;
import com.entity.AadhaarEntity;
import com.entity.PANEntity;
import com.repository.AadhaarRepository;
import com.repository.PANRepository;
import com.service.KYCService;
import com.utils.EncryptionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class KYCServiceTests {

    @Autowired
    private KYCService kycService;

    @Autowired
    private PANRepository panRepository;

    @Autowired
    private AadhaarRepository aadhaarRepository;

    @BeforeEach
    void setUp() {
        PANEntity panEntity = new PANEntity();
        panEntity.setPanNumber("ABCDE1234F");
        panEntity.setFullName("Ravi Sharma");
        panEntity.setDateOfBirth("1990-01-01");
        panEntity.setMobile("9876543210");
        panRepository.save(panEntity);

        AadhaarEntity aadhaarEntity = new AadhaarEntity();
        aadhaarEntity.setAadhaarNumber("123456789012");
        aadhaarEntity.setFullName("Ravi Sharma");
        aadhaarEntity.setDateOfBirth("1992-02-02");
        aadhaarEntity.setMobile("9876543210");
        aadhaarRepository.save(aadhaarEntity);
    }

    @Test
    void testVerifyPanConsentNotGiven() {
        PANRequest request = new PANRequest();
        request.setPan("ABCDE1234F");
        request.setConsentGiven(false);

        Exception exception = assertThrows(Exception.class, () -> kycService.verifyPan(request));
        assertEquals("CONSENT_NOT_GIVEN", exception.getMessage());
    }

    @Test
    void testVerifyPanInvalidFormat() throws Exception {
        PANRequest request = new PANRequest();
        request.setPan(EncryptionUtil.encrypt("INVALIDPAN"));
        request.setConsentGiven(true);

        Exception exception = assertThrows(Exception.class, () -> kycService.verifyPan(request));
        assertEquals("INVALID_PAN_FORMAT", exception.getMessage());
    }

    @Test
    void testVerifyPanNotFound() throws Exception {
        PANRequest request = new PANRequest();
        request.setPan(EncryptionUtil.encrypt("NOTEX1234E"));
        request.setConsentGiven(true);

        Exception exception = assertThrows(Exception.class, () -> kycService.verifyPan(request));
        assertEquals("PAN_NOT_FOUND", exception.getMessage());
    }

    @Test
    void testVerifyPanSuccess() throws Exception {
        PANRequest request = new PANRequest();
        request.setPan(EncryptionUtil.encrypt("ABCDE1234F"));
        request.setConsentGiven(true);

        PANResponse response = kycService.verifyPan(request);

        assertNotNull(response);
        assertTrue(response.isVerified());
        assertEquals("Ravi Sharma", response.getFullName());
        assertEquals("ABCDE1234F", response.getPanNumber());
    }

    @Test
    void testVerifyAadhaarConsentNotGiven() {
        AadhaarRequest request = new AadhaarRequest();
        request.setAadhaar("123456789012");
        request.setConsentGiven(false);

        Exception exception = assertThrows(Exception.class, () -> kycService.verifyAadhaar(request));
        assertEquals("CONSENT_NOT_GIVEN", exception.getMessage());
    }

    @Test
    void testVerifyAadhaarInvalidFormat() throws Exception {
        AadhaarRequest request = new AadhaarRequest();
        request.setAadhaar(EncryptionUtil.encrypt("INVALIDAADHAAR"));
        request.setConsentGiven(true);

        Exception exception = assertThrows(Exception.class, () -> kycService.verifyAadhaar(request));
        assertEquals("INVALID_AADHAAR_FORMAT", exception.getMessage());
    }

    @Test
    void testVerifyAadhaarNotFound() throws Exception {
        AadhaarRequest request = new AadhaarRequest();
        request.setAadhaar(EncryptionUtil.encrypt("000000000000"));
        request.setConsentGiven(true);

        Exception exception = assertThrows(Exception.class, () -> kycService.verifyAadhaar(request));
        assertEquals("AADHAAR_NOT_FOUND", exception.getMessage());
    }

    @Test
    void testVerifyAadhaarSuccess() throws Exception {
        AadhaarRequest request = new AadhaarRequest();
        request.setAadhaar(EncryptionUtil.encrypt("123456789012"));
        request.setConsentGiven(true);

        AadhaarResponse response = kycService.verifyAadhaar(request);

        assertNotNull(response);
        assertTrue(response.isVerified());
        assertEquals("Ravi Sharma", response.getFullName());
        assertEquals("123456789012", response.getAadhaarNumber());
    }
}