package com.ekcapaper.softwareledgerbackend.controller;

import com.ekcapaper.softwareledgerbackend.model.dto.TechnicalRelationshipDTO;
import com.ekcapaper.softwareledgerbackend.model.entity.TechnicalType;
import com.ekcapaper.softwareledgerbackend.service.TechnicalRelationshipService;
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
public class TechnicalRelationshipController {

    private final TechnicalRelationshipService relationshipService;

    @Operation(summary = "특정 상황에서 어떤 결정이 내려졌는지 조회",
            description = "특정 상황에서 어떤 결정이 내려졌는지 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TechnicalRelationshipDTO.class)))
    })
    @GetMapping("/context/{contextId}")
    public ResponseEntity<List<TechnicalRelationshipDTO>> getDecisionsByContext(@PathVariable Long contextId) {
        return ResponseEntity.ok(relationshipService.getDecisionsByContextId(contextId));
    }

    @Operation(summary = "특정 결정이 어떤 새로운 상황을 만들었는지 조회",
            description = "특정 결정이 어떤 새로운 상황을 만들었는지 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TechnicalRelationshipDTO.class)))
    })
    @GetMapping("/decision/{decisionId}")
    public ResponseEntity<List<TechnicalRelationshipDTO>> getNewContextsByDecision(@PathVariable Long decisionId) {
        return ResponseEntity.ok(relationshipService.getNewContextsByDecisionId(decisionId));
    }

    @Operation(summary = "새로운 관계 추가", description = "새로운 관계를 추가합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공적으로 생성됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TechnicalRelationshipDTO.class)))
    })
    @PostMapping
    public ResponseEntity<TechnicalRelationshipDTO> addRelationship(
            @RequestBody TechnicalRelationshipDTO relationshipDTO) {
        return ResponseEntity.ok(relationshipService.addRelationship(
                relationshipDTO.getSourceId(),
                relationshipDTO.getSourceType(),
                relationshipDTO.getTargetId(),
                relationshipDTO.getTargetType()
                )
        );
    }

    @Operation(summary = "관계 삭제", description = "특정 관계를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "성공적으로 삭제됨")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRelationship(@PathVariable Long id) {
        relationshipService.deleteRelationship(id);
        return ResponseEntity.noContent().build();
    }
}
