package com.ekcapaper.softwareledgerbackend.controller;

import com.ekcapaper.softwareledgerbackend.model.dto.TechnicalDecisionDTO;
import com.ekcapaper.softwareledgerbackend.model.entity.TechnicalDecision;
import com.ekcapaper.softwareledgerbackend.service.TechnicalDecisionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "모든 기술적 결정 조회", description = "등록된 모든 기술적 결정을 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨")
    })
    @GetMapping
    public ResponseEntity<List<TechnicalDecisionDTO>> getAllDecisions() {
        return ResponseEntity.ok(decisionService.getAllDecisions());
    }

    // 특정 기술적 결정 조회
    @Operation(summary = "특정 기술적 결정 조회", description = "주어진 ID에 해당하는 기술적 결정을 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨"),
            @ApiResponse(responseCode = "404", description = "해당 ID의 기술적 결정을 찾을 수 없음")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TechnicalDecisionDTO> getDecisionById(@PathVariable Long id) {
        Optional<TechnicalDecisionDTO> decision = decisionService.getDecisionById(id);
        return decision.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 새로운 기술적 결정 추가
    @Operation(summary = "새로운 기술적 결정 추가", description = "새로운 기술적 결정을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공적으로 생성됨")
    })
    @PostMapping
    public ResponseEntity<TechnicalDecisionDTO> createDecision(@RequestBody TechnicalDecisionDTO decisionDTO) {
        TechnicalDecision decision = new TechnicalDecision();
        decision.setName(decisionDTO.getDecision());
        decision.setDescription(decisionDTO.getImpact());
        return ResponseEntity.ok(decisionService.createDecision(decision));
    }

    // 기술적 결정 수정
    @Operation(summary = "기술적 결정 수정", description = "기술적 결정을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 수정됨")
    })
    @PutMapping("/{id}")
    public ResponseEntity<TechnicalDecisionDTO> updateDecision(@PathVariable Long id, @RequestBody TechnicalDecisionDTO decisionDTO) {
        TechnicalDecision decision = new TechnicalDecision();
        decision.setName(decisionDTO.getDecision());
        decision.setDescription(decisionDTO.getImpact());
        return ResponseEntity.ok(decisionService.updateDecision(id, decision));
    }

    // 기술적 결정 삭제
    @Operation(summary = "기술적 결정 삭제", description = "기술적 결정을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "성공적으로 삭제됨")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDecision(@PathVariable Long id) {
        decisionService.deleteDecision(id);
        return ResponseEntity.noContent().build();
    }
}
