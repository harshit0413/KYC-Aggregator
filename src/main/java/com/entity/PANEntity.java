package com.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "PANTable")
public class PANEntity {

    @Id
    @Column(name = "PANNo", length = 10, nullable = false)
    private String panNumber;

    @Column(name = "FullName", length = 50, nullable = false)
    private String fullName;

    @Column(name = "DOB")
    private String dateOfBirth;

    @Column(name = "Mobile", length = 15)
    private String mobile;

    @Column(name = "FatherName", length = 50)
    private String fatherName;

    @Column(name = "MotherName", length = 50)
    private String motherName;
}