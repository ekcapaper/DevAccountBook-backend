package com.example.softwareaccountbackend.service;

import com.example.softwareaccountbackend.model.entity.TechnicalRelationship;
import com.example.softwareaccountbackend.model.entity.TechnicalType;
import com.example.softwareaccountbackend.repository.TechnicalRelationshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnicalRelationshipService {

    private final TechnicalRelationshipRepository relationshipRepository;

    // 특정 상황에서 어떤 결정이 내려졌는지 조회
    public List<TechnicalRelationship> getDecisionsByContextId(Long contextId) {
        return relationshipRepository.findBySourceId(contextId);
    }

    // 특정 결정이 어떤 새로운 상황을 만들었는지 조회
    public List<TechnicalRelationship> getNewContextsByDecisionId(Long decisionId) {
        return relationshipRepository.findBySourceId(decisionId);
    }

    // 새로운 관계 추가 (상황 -> 결정, 결정 -> 새로운 상황)
    public TechnicalRelationship addRelationship(Long sourceId, TechnicalType sourceType, Long targetId, TechnicalType targetType) {
        TechnicalRelationship relationship = TechnicalRelationship.builder()
                .sourceId(sourceId)
                .sourceType(sourceType)
                .targetId(targetId)
                .targetType(targetType)
                .build();
        return relationshipRepository.save(relationship);
    }

    // 관계 삭제
    public void deleteRelationship(Long id) {
        relationshipRepository.deleteById(id);
    }
}