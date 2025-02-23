package com.ekcapaper.softwareledgerbackend.service;

import com.ekcapaper.softwareledgerbackend.model.dto.SoftwareEquityDTO;
import com.ekcapaper.softwareledgerbackend.model.entity.SoftwareEquity;
import com.ekcapaper.softwareledgerbackend.repository.SoftwareEquityRepository;
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

    public SoftwareEquityDTO createEquity(SoftwareEquityDTO equityDTO) {
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