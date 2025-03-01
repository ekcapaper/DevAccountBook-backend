package com.ekcapaper.software.accounting.backend.repository;


import com.ekcapaper.software.accounting.backend.model.entity.SoftwareLiability;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftwareLiabilityRepository extends JpaRepository<SoftwareLiability, Long> {
}
