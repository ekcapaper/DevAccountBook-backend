package com.ekcapaper.software.accounting.backend.service;

import com.ekcapaper.software.accounting.backend.model.dto.TechnicalRelationshipDTO;
import com.ekcapaper.software.accounting.backend.model.entity.TechnicalRelationship;
import com.ekcapaper.software.accounting.backend.model.entity.TechnicalType;
import com.ekcapaper.software.accounting.backend.repository.TechnicalRelationshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TechnicalRelationshipService {

    private final TechnicalRelationshipRepository relationshipRepository;

    // 특정 상황에서 어떤 결정이 내려졌는지 조회 (DTO 변환)
    public List<TechnicalRelationshipDTO> getDecisionsByContextId(Long contextId) {
        return relationshipRepository.findBySourceId(contextId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 특정 결정이 어떤 새로운 상황을 만들었는지 조회 (DTO 변환)
    public List<TechnicalRelationshipDTO> getNewContextsByDecisionId(Long decisionId) {
        return relationshipRepository.findBySourceId(decisionId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 새로운 관계 추가
    public TechnicalRelationshipDTO addRelationship(Long sourceId, TechnicalType sourceType, Long targetId, TechnicalType targetType) {
        TechnicalRelationship relationship = TechnicalRelationship.builder()
                .sourceId(sourceId)
                .sourceType(sourceType)
                .targetId(targetId)
                .targetType(targetType)
                .build();
        return convertToDTO(relationshipRepository.save(relationship));
    }

    // 관계 삭제
    public void deleteRelationship(Long id) {
        relationshipRepository.deleteById(id);
    }

    // 엔티티 → DTO 변환 메서드
    private TechnicalRelationshipDTO convertToDTO(TechnicalRelationship relationship) {
        TechnicalRelationshipDTO dto = new TechnicalRelationshipDTO();
        dto.setSourceId(relationship.getSourceId());
        dto.setSourceType(relationship.getSourceType());
        dto.setTargetId(relationship.getTargetId());
        dto.setTargetType(relationship.getTargetType());
        return dto;
    }
}
