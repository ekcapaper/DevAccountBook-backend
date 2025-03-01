package com.ekcapaper.software.accounting.backend.service;

import com.ekcapaper.software.accounting.backend.model.dto.SoftwareLiabilityDTO;
import com.ekcapaper.software.accounting.backend.model.entity.SoftwareLiability;
import com.ekcapaper.software.accounting.backend.repository.SoftwareLiabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SoftwareLiabilityService {

    private final SoftwareLiabilityRepository liabilityRepository;

    public List<SoftwareLiabilityDTO> getAllLiabilities() {
        return liabilityRepository.findAll()
                .stream()
                .map(liability -> new SoftwareLiabilityDTO(liability.getId(), liability.getName(), liability.getDescription()))
                .collect(Collectors.toList());
    }

    public Optional<SoftwareLiabilityDTO> getLiabilityById(Long id) {
        return liabilityRepository.findById(id)
                .map(liability -> new SoftwareLiabilityDTO(liability.getId(), liability.getName(), liability.getDescription()));
    }

    public SoftwareLiabilityDTO createLiability(SoftwareLiabilityDTO liabilityDTO) {
        SoftwareLiability liability = new SoftwareLiability();
        liability.setName(liabilityDTO.getName());
        liability.setDescription(liabilityDTO.getDescription());

        SoftwareLiability savedLiability = liabilityRepository.save(liability);
        return new SoftwareLiabilityDTO(savedLiability.getId(), savedLiability.getName(), savedLiability.getDescription());
    }

    public void deleteLiability(Long id) {
        liabilityRepository.deleteById(id);
    }
}