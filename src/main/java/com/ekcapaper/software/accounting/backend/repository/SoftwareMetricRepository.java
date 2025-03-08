package com.ekcapaper.software.accounting.backend.repository;

import com.ekcapaper.software.accounting.backend.model.entity.SoftwareMetric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftwareMetricRepository extends JpaRepository<SoftwareMetric, Long> {
}
