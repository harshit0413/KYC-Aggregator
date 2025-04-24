package com.repository;

import com.entity.PANEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PANRepository extends JpaRepository<PANEntity, String> {
    PANEntity findByPanNumber(String panNumber);
}