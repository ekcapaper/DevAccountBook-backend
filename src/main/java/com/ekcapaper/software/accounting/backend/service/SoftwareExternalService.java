package com.ekcapaper.software.accounting.backend.service;

import com.ekcapaper.software.accounting.backend.model.dto.SoftwareEquityCreateUpdateDTO;
import com.ekcapaper.software.accounting.backend.model.dto.SoftwareEquityDTO;
import com.ekcapaper.software.accounting.backend.model.dto.SoftwareExternalCreateUpdateDTO;
import com.ekcapaper.software.accounting.backend.model.dto.SoftwareExternalDTO;
import com.ekcapaper.software.accounting.backend.model.entity.SoftwareEquity;
import com.ekcapaper.software.accounting.backend.model.entity.SoftwareExternal;
import com.ekcapaper.software.accounting.backend.repository.SoftwareExternalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SoftwareExternalService {
    private final SoftwareExternalRepository softwareExternalRepository;

    public List<SoftwareExternalDTO> getAllSoftwareExternals() {
        return softwareExternalRepository.findAll()
                .stream()
                .map(softwareExternal -> new SoftwareExternalDTO(softwareExternal.getId(), softwareExternal.getName(), softwareExternal.getDescription()))
                .collect(Collectors.toList());
    }

    public Optional<SoftwareExternalDTO> getSoftwareExternalById(Long id) {
        return softwareExternalRepository.findById(id)
                .map(softwareExternal -> new SoftwareExternalDTO(softwareExternal.getId(), softwareExternal.getName(), softwareExternal.getDescription()));
    }

    public SoftwareExternalDTO createSoftwareExternal(SoftwareExternalCreateUpdateDTO softwareExternalCreateUpdateDTO) {
        SoftwareExternal softwareExternal = new SoftwareExternal();
        softwareExternal.setName(softwareExternalCreateUpdateDTO.getName());
        softwareExternal.setDescription(softwareExternalCreateUpdateDTO.getDescription());

        SoftwareExternal softwareExternal1 = softwareExternalRepository.save(softwareExternal);
        return new SoftwareExternalDTO(softwareExternal1.getId(), softwareExternal1.getName(), softwareExternal1.getDescription());
    }

    public void deleteSoftwareExternal(Long id) {
        softwareExternalRepository.deleteById(id);
    }


    @Transactional
    public SoftwareExternalDTO updateSoftwareExternal(Long id, SoftwareExternalCreateUpdateDTO softwareExternalCreateUpdateDTO) {
        return softwareExternalRepository.findById(id)
                .map(softwareExternal -> {
                    softwareExternal.setName(softwareExternalCreateUpdateDTO.getName());
                    softwareExternal.setDescription(softwareExternalCreateUpdateDTO.getDescription());
                    return new SoftwareExternalDTO(softwareExternal.getId(), softwareExternal.getName(), softwareExternal.getDescription());
                })
                .orElseThrow(() -> new RuntimeException("Technical Context not found"));
    }
}
