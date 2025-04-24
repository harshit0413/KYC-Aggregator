package com.repository;

import com.entity.AadhaarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AadhaarRepository extends JpaRepository<AadhaarEntity, String> {
    AadhaarEntity findByAadhaarNumber(String aadhaarNumber);
}