package com.ekcapaper.software.accounting.backend.repository;

import com.ekcapaper.software.accounting.backend.model.entity.ProjectMetric;
import com.ekcapaper.software.accounting.backend.model.entity.SoftwareAsset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectMetricRepository extends JpaRepository<ProjectMetric, Long> {
}
