package com.ekcapaper.softwareaccountbackend.repository;


import com.ekcapaper.softwareaccountbackend.model.entity.SoftwareEquity;
import com.ekcapaper.softwareaccountbackend.model.entity.SoftwareLiability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftwareLiabilityRepository extends JpaRepository<SoftwareLiability, Long> {
}
