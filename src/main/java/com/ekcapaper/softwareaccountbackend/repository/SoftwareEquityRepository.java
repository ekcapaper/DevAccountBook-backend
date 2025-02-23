package com.ekcapaper.softwareaccountbackend.repository;

import com.ekcapaper.softwareaccountbackend.model.entity.SoftwareAsset;
import com.ekcapaper.softwareaccountbackend.model.entity.SoftwareEquity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftwareEquityRepository extends JpaRepository<SoftwareEquity, Long> {
}
