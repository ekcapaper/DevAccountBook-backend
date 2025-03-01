package com.ekcapaper.software.accounting.backend.service;

import com.ekcapaper.software.accounting.backend.model.dto.TechnicalRelationshipDTO;
import com.ekcapaper.software.accounting.backend.model.entity.TechnicalContext;
import com.ekcapaper.software.accounting.backend.model.entity.TechnicalDecision;
import com.ekcapaper.software.accounting.backend.model.entity.TechnicalRelationship;
import com.ekcapaper.software.accounting.backend.repository.TechnicalContextRepository;
import com.ekcapaper.software.accounting.backend.repository.TechnicalDecisionRepository;
import com.ekcapaper.software.accounting.backend.repository.TechnicalRelationshipRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TechnicalRelationshipService {
    private final TechnicalRelationshipRepository technicalRelationshipRepository;
    private final TechnicalContextRepository technicalContextRepository;
    private final TechnicalDecisionRepository technicalDecisionRepository;


    // 특정 상황에서 어떤 결정이 내려졌는지 조회 (DTO 변환)
    public List<TechnicalRelationshipDTO> getDecisionsByContextId(Long technicalContextId) {
        return technicalRelationshipRepository.findByTechnicalContextId(technicalContextId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 특정 결정이 어떤 새로운 상황을 만들었는지 조회 (DTO 변환)
    public List<TechnicalRelationshipDTO> getNewContextsByDecisionId(Long technicalDecisionId) {
        return technicalRelationshipRepository.findByTechnicalDecisionId(technicalDecisionId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 새로운 관계 추가
    public TechnicalRelationshipDTO addRelationship(Long technicalContextId, Long technicalDecisionId) {
        TechnicalContext technicalContext = technicalContextRepository.getReferenceById(technicalContextId);
        TechnicalDecision technicalDecision = technicalDecisionRepository.getReferenceById(technicalDecisionId);
        TechnicalRelationship technicalRelationship = TechnicalRelationship.builder()
                .technicalContext(technicalContext)
                .technicalDecision(technicalDecision)
                .build();
        return convertToDTO(technicalRelationshipRepository.save(technicalRelationship));
    }

    // 관계 삭제
    public void deleteRelationship(Long id) {
        technicalRelationshipRepository.deleteById(id);
    }

    // 엔티티 → DTO 변환 메서드
    private TechnicalRelationshipDTO convertToDTO(TechnicalRelationship relationship) {
        TechnicalRelationshipDTO dto = new TechnicalRelationshipDTO();
        dto.setId(relationship.getId());
        dto.setTechnicalContextId(relationship.getTechnicalContext().getId());
        dto.setTechnicalDecisionId(relationship.getTechnicalDecision().getId());
        return dto;
    }
}
