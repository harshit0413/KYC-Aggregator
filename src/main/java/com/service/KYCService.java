package com.service;

import com.dto.*;
import com.entity.*;
import com.exception.CustomException;
import com.repository.*;
import com.utils.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class KYCService {

    private final PANRepository panRepository;
    private final AadhaarRepository aadhaarRepository;
    private final KYCDetailsRepository kycDetailsRepository;

    public KYCService(PANRepository panRepository,
                      AadhaarRepository aadhaarRepository,
                      KYCDetailsRepository kycDetailsRepository) {
        this.panRepository = panRepository;
        this.aadhaarRepository = aadhaarRepository;
        this.kycDetailsRepository = kycDetailsRepository;
    }

    public PANResponse verifyPan(PANRequest request) throws Exception {
        if (!request.isConsentGiven()) {
            throw new CustomException("CONSENT_NOT_GIVEN");
        }

        String decryptedPan = EncryptionUtil.decrypt(request.getPan());
        if (!ValidationUtil.isValidPan(decryptedPan)) {
            throw new CustomException("INVALID_PAN_FORMAT");
        }

        KYCDetails kycDetails = new KYCDetails();
        kycDetails.setKycDocType("PAN");
        kycDetails.setKycDocId(decryptedPan);
        kycDetails.setConsentGiven(true);
        kycDetails.setCreatedOn(LocalDateTime.now());
        kycDetails.setModifiedOn(LocalDateTime.now());
        kycDetailsRepository.save(kycDetails);

        PANEntity panEntity = panRepository.findByPanNumber(decryptedPan);
        if (panEntity == null) {
            throw new CustomException("PAN_NOT_FOUND");
        }

        return new PANResponse(
                true,
                panEntity.getFullName(),
                panEntity.getDateOfBirth(),
                panEntity.getPanNumber(),
                panEntity.getMobile(),
                panEntity.getFatherName(),
                panEntity.getMotherName()
        );
    }

    public AadhaarResponse verifyAadhaar(AadhaarRequest request) throws Exception {
        if (!request.isConsentGiven()) {
            throw new CustomException("CONSENT_NOT_GIVEN");
        }

        String decryptedAadhaar = EncryptionUtil.decrypt(request.getAadhaar());
        if (!ValidationUtil.isValidAadhaar(decryptedAadhaar)) {
            throw new CustomException("INVALID_AADHAAR_FORMAT");
        }

        KYCDetails kycDetails = new KYCDetails();
        kycDetails.setKycDocType("Aadhaar");
        kycDetails.setKycDocId(decryptedAadhaar);
        kycDetails.setConsentGiven(true);
        kycDetails.setCreatedOn(LocalDateTime.now());
        kycDetails.setModifiedOn(LocalDateTime.now());
        kycDetailsRepository.save(kycDetails);

        AadhaarEntity aadhaarEntity = aadhaarRepository.findByAadhaarNumber(decryptedAadhaar);
        if (aadhaarEntity == null) {
            throw new CustomException("AADHAAR_NOT_FOUND");
        }

        return new AadhaarResponse(
                true,
                aadhaarEntity.getFullName(),
                aadhaarEntity.getDateOfBirth(),
                aadhaarEntity.getAadhaarNumber(),
                aadhaarEntity.getGender(),
                aadhaarEntity.getMobile(),
                aadhaarEntity.getEmail(),
                aadhaarEntity.getFatherName(),
                aadhaarEntity.getMotherName(),
                aadhaarEntity.getAddressLine1(),
                aadhaarEntity.getAddressLine2(),
                aadhaarEntity.getCity(),
                aadhaarEntity.getState(),
                aadhaarEntity.getPin()
        );
    }
}