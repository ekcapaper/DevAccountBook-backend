package com.ekcapaper.softwareledgerbackend.repository;

import com.ekcapaper.softwareledgerbackend.model.entity.SoftwareAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftwareAssetRepository extends JpaRepository<SoftwareAsset, Long> {
}
