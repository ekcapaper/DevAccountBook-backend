package com.ekcapaper.software.accounting.backend.service;

import com.ekcapaper.software.accounting.backend.converter.TechnicalContextDecisionConverter;
import com.ekcapaper.software.accounting.backend.model.dto.TechnicalContextDecisionDTO;
import com.ekcapaper.software.accounting.backend.model.entity.TechnicalContext;
import com.ekcapaper.software.accounting.backend.model.entity.TechnicalDecision;
import com.ekcapaper.software.accounting.backend.model.entity.TechnicalContextDecision;
import com.ekcapaper.software.accounting.backend.repository.TechnicalContextRepository;
import com.ekcapaper.software.accounting.backend.repository.TechnicalDecisionRepository;
import com.ekcapaper.software.accounting.backend.repository.TechnicalContextDecisionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TechnicalContextDecisionService {
    private final TechnicalContextDecisionRepository technicalContextDecisionRepository;
    private final TechnicalContextRepository technicalContextRepository;
    private final TechnicalDecisionRepository technicalDecisionRepository;

    private final TechnicalContextService technicalContextService;
    private final TechnicalDecisionService technicalDecisionService;

    private final TechnicalContextDecisionConverter technicalContextDecisionConverter;

    // 특정 상황에서 어떤 결정이 내려졌는지 조회 (DTO 변환)
    public List<TechnicalContextDecisionDTO> getDecisionsByContextId(Long technicalContextId) {
        return technicalContextDecisionRepository.findByTechnicalContextId(technicalContextId).stream()
                .map(technicalContextDecisionConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    // 특정 결정이 어떤 새로운 상황을 만들었는지 조회 (DTO 변환)
    public List<TechnicalContextDecisionDTO> getNewContextsByDecisionId(Long technicalDecisionId) {
        return technicalContextDecisionRepository.findByTechnicalDecisionId(technicalDecisionId).stream()
                .map(technicalContextDecisionConverter::convertToDTO)
                .collect(Collectors.toList());
    }

    // 새로운 관계 추가
    public TechnicalContextDecisionDTO addRelationship(Long technicalContextId, Long technicalDecisionId) {
        TechnicalContext technicalContext = technicalContextRepository.findById(technicalContextId).orElseGet(()->null);
        TechnicalDecision technicalDecision = technicalDecisionRepository.findById(technicalContextId).orElseGet(()->null);
        TechnicalContextDecision technicalContextDecision = TechnicalContextDecision.builder()
                .technicalContext(technicalContext)
                .technicalDecision(technicalDecision)
                .build();
        return technicalContextDecisionConverter.convertToDTO(technicalContextDecisionRepository.save(technicalContextDecision));
    }

    // 관계 삭제
    public void deleteRelationship(Long id) {
        technicalContextDecisionRepository.deleteById(id);
    }

    //
    public List<TechnicalContextDecisionDTO> getContextDecisionById(Long id) {
        return technicalContextDecisionRepository.findById(id).stream().map(technicalContextDecisionConverter::convertToDTO).collect(Collectors.toList());
    }

    public List<TechnicalContextDecisionDTO> getContextDecisionAll() {
        return technicalContextDecisionRepository.findAll().stream().map(technicalContextDecisionConverter::convertToDTO).collect(Collectors.toList());
    }
}
