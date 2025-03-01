package com.ekcapaper.software.accounting.backend.repository;

import com.ekcapaper.software.accounting.backend.model.entity.TechnicalRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnicalRelationshipRepository extends JpaRepository<TechnicalRelationship, Long> {
    List<TechnicalRelationship> findByTechnicalContextId(Long technicalContextId);
    List<TechnicalRelationship> findByTechnicalDecisionId(Long technicalDecisionId);
}
