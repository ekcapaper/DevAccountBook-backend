package com.ekcapaper.software.accounting.backend.repository;

import com.ekcapaper.software.accounting.backend.model.entity.TechnicalContextDecision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnicalContextDecisionRepository extends JpaRepository<TechnicalContextDecision, Long> {
    List<TechnicalContextDecision> findByTechnicalContextId(Long technicalContextId);
    List<TechnicalContextDecision> findByTechnicalDecisionId(Long technicalDecisionId);
}
