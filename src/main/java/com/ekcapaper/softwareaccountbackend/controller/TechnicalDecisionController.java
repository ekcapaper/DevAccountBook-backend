package com.ekcapaper.softwareaccountbackend.controller;

import com.ekcapaper.softwareaccountbackend.model.dto.TechnicalDecisionDTO;
import com.ekcapaper.softwareaccountbackend.model.entity.TechnicalDecision;
import com.ekcapaper.softwareaccountbackend.service.TechnicalDecisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/decisions")
@RequiredArgsConstructor
public class TechnicalDecisionController {

    private final TechnicalDecisionService decisionService;

    // 모든 기술적 결정 조회
    @GetMapping
    public ResponseEntity<List<TechnicalDecisionDTO>> getAllDecisions() {
        return ResponseEntity.ok(decisionService.getAllDecisions());
    }

    // 특정 기술적 결정 조회
    @GetMapping("/{id}")
    public ResponseEntity<TechnicalDecisionDTO> getDecisionById(@PathVariable Long id) {
        Optional<TechnicalDecisionDTO> decision = decisionService.getDecisionById(id);
        return decision.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 새로운 기술적 결정 추가
    @PostMapping
    public ResponseEntity<TechnicalDecisionDTO> createDecision(@RequestBody TechnicalDecisionDTO decisionDTO) {
        TechnicalDecision decision = new TechnicalDecision();
        decision.setDecision(decisionDTO.getDecision());
        decision.setImpact(decisionDTO.getImpact());
        return ResponseEntity.ok(decisionService.createDecision(decision));
    }

    // 기술적 결정 수정
    @PutMapping("/{id}")
    public ResponseEntity<TechnicalDecisionDTO> updateDecision(@PathVariable Long id, @RequestBody TechnicalDecisionDTO decisionDTO) {
        TechnicalDecision decision = new TechnicalDecision();
        decision.setDecision(decisionDTO.getDecision());
        decision.setImpact(decisionDTO.getImpact());
        return ResponseEntity.ok(decisionService.updateDecision(id, decision));
    }

    // 기술적 결정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDecision(@PathVariable Long id) {
        decisionService.deleteDecision(id);
        return ResponseEntity.noContent().build();
    }
}
