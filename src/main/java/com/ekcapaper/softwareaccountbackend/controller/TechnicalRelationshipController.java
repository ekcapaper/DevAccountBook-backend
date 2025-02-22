package com.ekcapaper.softwareaccountbackend.controller;

import com.ekcapaper.softwareaccountbackend.model.dto.TechnicalRelationshipDTO;
import com.ekcapaper.softwareaccountbackend.model.entity.TechnicalType;
import com.ekcapaper.softwareaccountbackend.service.TechnicalRelationshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relationships")
@RequiredArgsConstructor
public class TechnicalRelationshipController {

    private final TechnicalRelationshipService relationshipService;

    // 특정 상황에서 어떤 결정이 내려졌는지 조회
    @GetMapping("/context/{contextId}")
    public ResponseEntity<List<TechnicalRelationshipDTO>> getDecisionsByContext(@PathVariable Long contextId) {
        return ResponseEntity.ok(relationshipService.getDecisionsByContextId(contextId));
    }

    // 특정 결정이 어떤 새로운 상황을 만들었는지 조회
    @GetMapping("/decision/{decisionId}")
    public ResponseEntity<List<TechnicalRelationshipDTO>> getNewContextsByDecision(@PathVariable Long decisionId) {
        return ResponseEntity.ok(relationshipService.getNewContextsByDecisionId(decisionId));
    }

    // 새로운 관계 추가
    @PostMapping
    public ResponseEntity<TechnicalRelationshipDTO> addRelationship(
            @RequestParam Long sourceId,
            @RequestParam TechnicalType sourceType,
            @RequestParam Long targetId,
            @RequestParam TechnicalType targetType) {
        return ResponseEntity.ok(relationshipService.addRelationship(sourceId, sourceType, targetId, targetType));
    }

    // 관계 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRelationship(@PathVariable Long id) {
        relationshipService.deleteRelationship(id);
        return ResponseEntity.noContent().build();
    }
}
