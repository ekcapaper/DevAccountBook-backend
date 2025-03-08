package com.ekcapaper.software.accounting.backend.service;

import com.ekcapaper.software.accounting.backend.model.dto.*;
import com.ekcapaper.software.accounting.backend.model.entity.SoftwareMetric;
import com.ekcapaper.software.accounting.backend.repository.SoftwareMetricRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SoftwareMetricService {

    private final SoftwareMetricRepository softwareMetricRepository;

    public List<SoftwareMetricDTO> getAllProjectMetrics() {
        return softwareMetricRepository.findAll()
                .stream()
                .map(softwareMetric -> new SoftwareMetricDTO(softwareMetric.getId(), softwareMetric.getName(), softwareMetric.getDescription()))
                .collect(Collectors.toList());
    }

    public Optional<SoftwareMetricDTO> getProjectMetricById(Long id) {
        return softwareMetricRepository.findById(id)
                .map(softwareMetric -> new SoftwareMetricDTO(softwareMetric.getId(), softwareMetric.getName(), softwareMetric.getDescription()));
    }

    public SoftwareMetricDTO createProjectMetricDTO(SoftwareMetricCreateUpdateDTO softwareMetricCreateUpdateDTO) {
        SoftwareMetric softwareMetric = new SoftwareMetric();
        softwareMetric.setName(softwareMetricCreateUpdateDTO.getName());
        softwareMetric.setDescription(softwareMetricCreateUpdateDTO.getDescription());
        SoftwareMetric savedSoftwareMetric = softwareMetricRepository.save(softwareMetric);
        return new SoftwareMetricDTO(savedSoftwareMetric.getId(), savedSoftwareMetric.getName(), savedSoftwareMetric.getDescription());
    }

    public void deleteAsset(Long id) {
        softwareMetricRepository.deleteById(id);
    }

    @Transactional
    public SoftwareMetricDTO updateAsset(Long id, SoftwareMetricCreateUpdateDTO softwareMetricCreateUpdateDTO) {
        return softwareMetricRepository.findById(id)
                .map(softwareMetric -> {
                    softwareMetric.setName(softwareMetricCreateUpdateDTO.getName());
                    softwareMetric.setDescription(softwareMetricCreateUpdateDTO.getDescription());
                    return new SoftwareMetricDTO(softwareMetric.getId(), softwareMetric.getName(), softwareMetric.getDescription());
                })
                .orElseThrow(() -> new RuntimeException("Technical Context not found"));
    }
}
