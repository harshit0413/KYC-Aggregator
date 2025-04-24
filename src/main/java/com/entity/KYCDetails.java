package com.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "KYCDetails")
public class KYCDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "KID")
    private int kid;

    @Column(name = "KYCDocType", nullable = false, length = 30)
    private String kycDocType;

    @Column(name = "KYCDocID", nullable = false, length = 255)
    private String kycDocId;

    @Column(name = "ConsentGiven", nullable = false)
    private boolean consentGiven;

    @Column(name = "CreatedOn")
    private LocalDateTime createdOn;

    @Column(name = "CreatedBy", length = 50)
    private String createdBy;

    @Column(name = "ModifiedOn")
    private LocalDateTime modifiedOn;

    @Column(name = "ModifiedBy", length = 50)
    private String modifiedBy;
}