package com.ekcapaper.software.accounting.backend.service;

import com.ekcapaper.software.accounting.backend.model.dto.SoftwareAssetDTO;
import com.ekcapaper.software.accounting.backend.model.entity.SoftwareAsset;
import com.ekcapaper.software.accounting.backend.repository.SoftwareAssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SoftwareAssetService {

    private final SoftwareAssetRepository assetRepository;

    public List<SoftwareAssetDTO> getAllAssets() {
        return assetRepository.findAll()
                .stream()
                .map(asset -> new SoftwareAssetDTO(asset.getId(), asset.getName(), asset.getDescription()))
                .collect(Collectors.toList());
    }

    public Optional<SoftwareAssetDTO> getAssetById(Long id) {
        return assetRepository.findById(id)
                .map(asset -> new SoftwareAssetDTO(asset.getId(), asset.getName(), asset.getDescription()));
    }

    public SoftwareAssetDTO createAsset(SoftwareAssetDTO assetDTO) {
        SoftwareAsset asset = new SoftwareAsset();
        asset.setName(assetDTO.getName());
        asset.setDescription(assetDTO.getDescription());

        SoftwareAsset savedAsset = assetRepository.save(asset);
        return new SoftwareAssetDTO(savedAsset.getId(), savedAsset.getName(), savedAsset.getDescription());
    }

    public void deleteAsset(Long id) {
        assetRepository.deleteById(id);
    }
}