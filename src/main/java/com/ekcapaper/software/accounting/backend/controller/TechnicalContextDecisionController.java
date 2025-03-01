package com.ekcapaper.software.accounting.backend.controller;

import com.ekcapaper.software.accounting.backend.model.dto.TechnicalContextDecisionCreateDTO;
import com.ekcapaper.software.accounting.backend.model.dto.TechnicalContextDecisionDTO;
import com.ekcapaper.software.accounting.backend.service.TechnicalContextDecisionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/relationships")
@RequiredArgsConstructor
public class TechnicalContextDecisionController {

    private final TechnicalContextDecisionService technicalContextDecisionService;

    @Operation(summary = "특정 상황에서 어떤 결정이 내려졌는지 조회",
            description = "특정 상황에서 어떤 결정이 내려졌는지 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TechnicalContextDecisionDTO.class)))
    })
    @GetMapping("/context/{contextId}")
    public ResponseEntity<List<TechnicalContextDecisionDTO>> getDecisionsByContext(@PathVariable Long contextId) {
        return ResponseEntity.ok(technicalContextDecisionService.getDecisionsByContextId(contextId));
    }

    @Operation(summary = "특정 결정이 어떤 새로운 상황을 만들었는지 조회",
            description = "특정 결정이 어떤 새로운 상황을 만들었는지 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TechnicalContextDecisionDTO.class)))
    })
    @GetMapping("/decision/{decisionId}")
    public ResponseEntity<List<TechnicalContextDecisionDTO>> getNewContextsByDecision(@PathVariable Long decisionId) {
        return ResponseEntity.ok(technicalContextDecisionService.getNewContextsByDecisionId(decisionId));
    }

    @Operation(summary = "새로운 관계 추가", description = "새로운 관계를 추가합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공적으로 생성됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TechnicalContextDecisionDTO.class)))
    })
    @PostMapping
    public ResponseEntity<TechnicalContextDecisionDTO> addRelationship(
            @RequestBody TechnicalContextDecisionCreateDTO technicalContextDecisionCreateDTO) {
        return ResponseEntity.ok(technicalContextDecisionService.addRelationship(
                technicalContextDecisionCreateDTO.getTechnicalContextId(),
                technicalContextDecisionCreateDTO.getTechnicalDecisionId()
                )
        );
    }

    @Operation(summary = "관계 삭제", description = "특정 관계를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "성공적으로 삭제됨")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRelationship(@PathVariable Long id) {
        technicalContextDecisionService.deleteRelationship(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "관계 데이터 조회",
            description = "관계 데이터 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TechnicalContextDecisionDTO.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<TechnicalContextDecisionDTO> getRelationshipById(@PathVariable Long id) {
        return ResponseEntity.ok(
                technicalContextDecisionService.getContextDecisionById(id).getFirst()
        );
    }

    @GetMapping("/")
    public ResponseEntity<List<TechnicalContextDecisionDTO>> getAllRelationships() {
        return ResponseEntity.ok(technicalContextDecisionService.getContextDecisionAll());
    }

}
