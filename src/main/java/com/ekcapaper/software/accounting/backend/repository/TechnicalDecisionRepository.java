package com.ekcapaper.software.accounting.backend.repository;

import com.ekcapaper.software.accounting.backend.model.entity.TechnicalDecision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalDecisionRepository extends JpaRepository<TechnicalDecision, Long> {
}
