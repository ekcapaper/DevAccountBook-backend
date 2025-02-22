package com.example.softwareaccountbackend.repository;

import com.example.softwareaccountbackend.model.entity.TechnicalContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalDecisionRepository extends JpaRepository<TechnicalContext, Long> {
}
