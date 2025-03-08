package com.ekcapaper.software.accounting.backend.service;

import com.ekcapaper.software.accounting.backend.model.dto.*;
import com.ekcapaper.software.accounting.backend.model.entity.ProjectMetric;
import com.ekcapaper.software.accounting.backend.model.entity.SoftwareAsset;
import com.ekcapaper.software.accounting.backend.model.entity.TechnicalContext;
import com.ekcapaper.software.accounting.backend.repository.ProjectMetricRepository;
import com.ekcapaper.software.accounting.backend.repository.SoftwareAssetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectMetricService {

    private final ProjectMetricRepository projectMetricRepository;

    public List<ProjectMetricDTO> getAllProjectMetrics() {
        return projectMetricRepository.findAll()
                .stream()
                .map(projectMetric -> new ProjectMetricDTO(projectMetric.getId(), projectMetric.getName(), projectMetric.getDescription()))
                .collect(Collectors.toList());
    }

    public Optional<ProjectMetricDTO> getProjectMetricById(Long id) {
        return projectMetricRepository.findById(id)
                .map(projectMetric -> new ProjectMetricDTO(projectMetric.getId(), projectMetric.getName(), projectMetric.getDescription()));
    }

    public ProjectMetricDTO createProjectMetricDTO(ProjectMetricCreateDTO projectMetricCreateDTO) {
        ProjectMetric projectMetric = new ProjectMetric();
        projectMetric.setName(projectMetricCreateDTO.getName());
        projectMetric.setDescription(projectMetricCreateDTO.getDescription());
        ProjectMetric savedProjectMetric = projectMetricRepository.save(projectMetric);
        return new ProjectMetricDTO(savedProjectMetric.getId(), savedProjectMetric.getName(), savedProjectMetric.getDescription());
    }

    public void deleteAsset(Long id) {
        projectMetricRepository.deleteById(id);
    }

    @Transactional
    public ProjectMetricDTO updateAsset(Long id, ProjectMetricCreateDTO projectMetricCreateDTO) {
        return projectMetricRepository.findById(id)
                .map(projectMetric -> {
                    projectMetric.setName(projectMetricCreateDTO.getName());
                    projectMetric.setDescription(projectMetricCreateDTO.getDescription());
                    return new ProjectMetricDTO(projectMetric.getId(), projectMetric.getName(), projectMetric.getDescription());
                })
                .orElseThrow(() -> new RuntimeException("Technical Context not found"));
    }
}
