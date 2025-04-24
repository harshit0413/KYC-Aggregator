package com.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "AadhaarTable")
public class AadhaarEntity {

    @Id
    @Column(name = "AadhaarNo", length = 12, nullable = false)
    private String aadhaarNumber;

    @Column(name = "FullName", length = 50, nullable = false)
    private String fullName;

    @Column(name = "DOB")
    private String dateOfBirth;

    @Column(name = "Gender", length = 1)
    private String gender;

    @Column(name = "AddressLine1", length = 255)
    private String addressLine1;

    @Column(name = "AddressLine2", length = 255)
    private String addressLine2;

    @Column(name = "City", length = 100)
    private String city;

    @Column(name = "State", length = 100)
    private String state;

    @Column(name = "PIN", length = 6)
    private String pin;

    @Column(name = "Mobile", length = 15)
    private String mobile;

    @Column(name = "Email", length = 30)
    private String email;

    @Column(name = "FatherName", length = 50, nullable = false)
    private String fatherName;

    @Column(name = "MotherName", length = 50, nullable = false)
    private String motherName;
}