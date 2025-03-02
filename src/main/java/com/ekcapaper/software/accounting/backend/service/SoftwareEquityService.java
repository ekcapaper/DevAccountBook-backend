package com.ekcapaper.software.accounting.backend.service;

import com.ekcapaper.software.accounting.backend.model.dto.SoftwareEquityCreateDTO;
import com.ekcapaper.software.accounting.backend.model.dto.SoftwareEquityDTO;
import com.ekcapaper.software.accounting.backend.model.entity.SoftwareEquity;
import com.ekcapaper.software.accounting.backend.repository.SoftwareEquityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SoftwareEquityService {

    private final SoftwareEquityRepository equityRepository;

    public List<SoftwareEquityDTO> getAllEquities() {
        return equityRepository.findAll()
                .stream()
                .map(equity -> new SoftwareEquityDTO(equity.getId(), equity.getName(), equity.getDescription()))
                .collect(Collectors.toList());
    }

    public Optional<SoftwareEquityDTO> getEquityById(Long id) {
        return equityRepository.findById(id)
                .map(equity -> new SoftwareEquityDTO(equity.getId(), equity.getName(), equity.getDescription()));
    }

    public SoftwareEquityDTO createEquity(SoftwareEquityCreateDTO equityDTO) {
        SoftwareEquity equity = new SoftwareEquity();
        equity.setName(equityDTO.getName());
        equity.setDescription(equityDTO.getDescription());

        SoftwareEquity savedEquity = equityRepository.save(equity);
        return new SoftwareEquityDTO(savedEquity.getId(), savedEquity.getName(), savedEquity.getDescription());
    }

    public void deleteEquity(Long id) {
        equityRepository.deleteById(id);
    }
}