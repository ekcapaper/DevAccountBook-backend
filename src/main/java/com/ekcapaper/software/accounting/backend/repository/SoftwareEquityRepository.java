package com.ekcapaper.software.accounting.backend.repository;

import com.ekcapaper.software.accounting.backend.model.entity.SoftwareEquity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftwareEquityRepository extends JpaRepository<SoftwareEquity, Long> {
}
