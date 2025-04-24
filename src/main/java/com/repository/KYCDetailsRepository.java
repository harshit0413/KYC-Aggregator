package com.repository;

import com.entity.KYCDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KYCDetailsRepository extends JpaRepository<KYCDetails, Integer> {
}