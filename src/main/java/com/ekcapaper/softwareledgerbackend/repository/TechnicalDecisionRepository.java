package com.ekcapaper.softwareledgerbackend.repository;

import com.ekcapaper.softwareledgerbackend.model.entity.TechnicalDecision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalDecisionRepository extends JpaRepository<TechnicalDecision, Long> {
}
