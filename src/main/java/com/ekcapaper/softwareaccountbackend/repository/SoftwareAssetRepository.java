package com.ekcapaper.softwareaccountbackend.repository;

import com.ekcapaper.softwareaccountbackend.model.entity.SoftwareAsset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoftwareAssetRepository extends JpaRepository<SoftwareAsset, Long> {
}
