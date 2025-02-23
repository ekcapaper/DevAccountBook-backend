package com.ekcapaper.softwareaccountbackend.service;

import com.ekcapaper.softwareaccountbackend.model.entity.SoftwareAsset;
import com.ekcapaper.softwareaccountbackend.repository.SoftwareAssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SoftwareAssetService {

    private final SoftwareAssetRepository assetRepository;

    /**
     * 모든 소프트웨어 자산을 조회합니다.
     */
    public List<SoftwareAsset> getAllAssets() {
        return assetRepository.findAll();
    }

    /**
     * ID로 특정 소프트웨어 자산을 조회합니다.
     */
    public Optional<SoftwareAsset> getAssetById(Long id) {
        return assetRepository.findById(id);
    }

    /**
     * 새로운 소프트웨어 자산을 추가합니다.
     */
    public SoftwareAsset createAsset(SoftwareAsset asset) {
        return assetRepository.save(asset);
    }

    /**
     * ID를 기반으로 소프트웨어 자산을 삭제합니다.
     */
    public void deleteAsset(Long id) {
        assetRepository.deleteById(id);
    }
}
