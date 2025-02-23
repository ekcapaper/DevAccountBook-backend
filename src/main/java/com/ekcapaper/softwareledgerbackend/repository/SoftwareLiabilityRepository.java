package com.ekcapaper.softwareledgerbackend.repository;


import com.ekcapaper.softwareledgerbackend.model.entity.SoftwareLiability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftwareLiabilityRepository extends JpaRepository<SoftwareLiability, Long> {
}
