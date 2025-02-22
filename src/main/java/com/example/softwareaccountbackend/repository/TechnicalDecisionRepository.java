package com.example.softwareaccountbackend.repository;

import com.example.softwareaccountbackend.model.entity.TechnicalContext;
import com.example.softwareaccountbackend.model.entity.TechnicalDecision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalDecisionRepository extends JpaRepository<TechnicalDecision, Long> {
}
