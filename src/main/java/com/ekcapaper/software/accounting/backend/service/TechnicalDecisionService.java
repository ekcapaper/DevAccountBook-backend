package com.ekcapaper.software.accounting.backend.service;

import com.ekcapaper.software.accounting.backend.converter.TechnicalDecisionConverter;
import com.ekcapaper.software.accounting.backend.model.dto.TechnicalDecisionDTO;
import com.ekcapaper.software.accounting.backend.model.entity.TechnicalDecision;
import com.ekcapaper.software.accounting.backend.repository.TechnicalDecisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TechnicalDecisionService {
    private final TechnicalDecisionConverter technicalContextConverter;
    private final TechnicalDecisionRepository decisionRepository;

    // 모든 기술적 결정 조회 (DTO 변환)
    public List<TechnicalDecisionDTO> getAllDecisions() {
        return decisionRepository.findAll().stream()
                .map(technicalContextConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    // 특정 기술적 결정 조회 (DTO 변환)
    public Optional<TechnicalDecisionDTO> getDecisionById(Long id) {
        return decisionRepository.findById(id).map(technicalContextConverter::convertToDTO);
    }

    // 새로운 기술적 결정 추가
    public TechnicalDecisionDTO createDecision(TechnicalDecision decision) {
        return technicalContextConverter.convertToDTO(decisionRepository.save(decision));
    }

    // 기술적 결정 수정
    @Transactional
    public TechnicalDecisionDTO updateDecision(Long id, TechnicalDecision updatedDecision) {
        return decisionRepository.findById(id)
                .map(existingDecision -> {
                    existingDecision.setName(updatedDecision.getName());
                    existingDecision.setDescription(updatedDecision.getDescription());
                    return technicalContextConverter.convertToDTO(decisionRepository.save(existingDecision));
                })
                .orElseThrow(() -> new RuntimeException("Technical Decision not found"));
    }

    // 기술적 결정 삭제
    public void deleteDecision(Long id) {
        decisionRepository.deleteById(id);
    }


}
