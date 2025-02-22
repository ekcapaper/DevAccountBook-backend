package com.ekcapaper.softwareaccountbackend.repository;

import com.ekcapaper.softwareaccountbackend.model.entity.TechnicalDecision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalDecisionRepository extends JpaRepository<TechnicalDecision, Long> {
}
