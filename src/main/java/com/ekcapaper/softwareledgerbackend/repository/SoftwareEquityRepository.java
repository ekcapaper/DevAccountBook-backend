package com.ekcapaper.softwareledgerbackend.repository;

import com.ekcapaper.softwareledgerbackend.model.entity.SoftwareEquity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftwareEquityRepository extends JpaRepository<SoftwareEquity, Long> {
}
