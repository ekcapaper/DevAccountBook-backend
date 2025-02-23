package com.ekcapaper.softwareledgerbackend.service;

import com.ekcapaper.softwareledgerbackend.model.dto.TechnicalDecisionDTO;
import com.ekcapaper.softwareledgerbackend.model.entity.TechnicalDecision;
import com.ekcapaper.softwareledgerbackend.repository.TechnicalDecisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TechnicalDecisionService {

    private final TechnicalDecisionRepository decisionRepository;

    // 모든 기술적 결정 조회 (DTO 변환)
    public List<TechnicalDecisionDTO> getAllDecisions() {
        return decisionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 특정 기술적 결정 조회 (DTO 변환)
    public Optional<TechnicalDecisionDTO> getDecisionById(Long id) {
        return decisionRepository.findById(id).map(this::convertToDTO);
    }

    // 새로운 기술적 결정 추가
    public TechnicalDecisionDTO createDecision(TechnicalDecision decision) {
        return convertToDTO(decisionRepository.save(decision));
    }

    // 기술적 결정 수정
    @Transactional
    public TechnicalDecisionDTO updateDecision(Long id, TechnicalDecision updatedDecision) {
        return decisionRepository.findById(id)
                .map(existingDecision -> {
                    existingDecision.setName(updatedDecision.getName());
                    existingDecision.setDescription(updatedDecision.getDescription());
                    return convertToDTO(decisionRepository.save(existingDecision));
                })
                .orElseThrow(() -> new RuntimeException("Technical Decision not found"));
    }

    // 기술적 결정 삭제
    public void deleteDecision(Long id) {
        decisionRepository.deleteById(id);
    }

    // 엔티티 → DTO 변환 메서드
    private TechnicalDecisionDTO convertToDTO(TechnicalDecision decision) {
        TechnicalDecisionDTO dto = new TechnicalDecisionDTO();
        dto.setId(decision.getId());
        dto.setDecision(decision.getName());
        dto.setImpact(decision.getDescription());
        return dto;
    }
}
