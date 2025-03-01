package com.ekcapaper.software.accounting.backend.repository;

import com.ekcapaper.software.accounting.backend.model.entity.TechnicalContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicalContextRepository extends JpaRepository<TechnicalContext, Long> {
}
