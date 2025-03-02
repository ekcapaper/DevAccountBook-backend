package com.ekcapaper.software.accounting.backend.controller;

import com.ekcapaper.software.accounting.backend.model.dto.SoftwareAssetCreateDTO;
import com.ekcapaper.software.accounting.backend.model.dto.SoftwareAssetDTO;
import com.ekcapaper.software.accounting.backend.model.dto.SoftwareEquityCreateDTO;
import com.ekcapaper.software.accounting.backend.model.dto.SoftwareEquityDTO;
import com.ekcapaper.software.accounting.backend.service.SoftwareEquityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equities")
@RequiredArgsConstructor
public class SoftwareEquityController {
    private final SoftwareEquityService equityService;

    @Operation(summary = "모든 소프트웨어 핵심 기술 조회", description = "등록된 모든 소프트웨어 핵심 기술을 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SoftwareEquityDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<SoftwareEquityDTO>> getAllEquities() {
        return ResponseEntity.ok(equityService.getAllEquities());
    }

    @Operation(summary = "특정 소프트웨어 핵심 기술 조회", description = "주어진 ID에 해당하는 소프트웨어 핵심 기술을 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SoftwareEquityDTO.class))),
            @ApiResponse(responseCode = "404", description = "해당 ID의 소프트웨어 핵심 기술을 찾을 수 없음")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SoftwareEquityDTO> getEquityById(@PathVariable Long id) {
        return equityService.getEquityById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "새로운 소프트웨어 핵심 기술 추가", description = "새로운 소프트웨어 핵심 기술을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공적으로 생성됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SoftwareEquityDTO.class)))
    })
    @PostMapping
    public ResponseEntity<SoftwareEquityDTO> createEquity(
            @RequestBody SoftwareEquityCreateDTO equityDTO) {
        return ResponseEntity.ok(equityService.createEquity(equityDTO));
    }

    @Operation(summary = "소프트웨어 핵심 기술 삭제", description = "주어진 ID에 해당하는 소프트웨어 핵심 기술을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "성공적으로 삭제됨"),
            @ApiResponse(responseCode = "404", description = "해당 ID의 소프트웨어 핵심 기술을 찾을 수 없음")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEquity(@PathVariable Long id) {
        equityService.deleteEquity(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "소프트웨어 핵심 기술 수정", description = "소프트웨어 핵심 기술을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 수정됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SoftwareEquityDTO.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<SoftwareEquityDTO> updateDecision(
            @PathVariable Long id,
            @RequestBody SoftwareEquityCreateDTO equityCreateDTO) {
        return ResponseEntity.ok(equityService.updateEquity(id, equityCreateDTO));
    }
}
