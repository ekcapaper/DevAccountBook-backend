package com.ekcapaper.software.accounting.backend.controller;

import com.ekcapaper.software.accounting.backend.model.dto.*;
import com.ekcapaper.software.accounting.backend.service.SoftwareMetricService;
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
@RequestMapping("/api/software/metrics")
@RequiredArgsConstructor
public class SoftwareMetricController {

    private final SoftwareMetricService softwareMetricService;

    @Operation(summary = "지표 조회", description = "등록된 모든 소프트웨어 지표을 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SoftwareMetricDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<SoftwareMetricDTO>> getAllProjectMetrics() {
        return ResponseEntity.ok(softwareMetricService.getAllProjectMetrics());
    }

    @Operation(summary = "특정 지표 조회", description = "주어진 ID에 해당하는 지표을 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SoftwareMetricDTO.class))),
            @ApiResponse(responseCode = "404", description = "해당 ID의 지표를 찾을 수 없음")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SoftwareMetricDTO> getProjectMetricsById(@PathVariable Long id) {
        return softwareMetricService.getProjectMetricById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "새로운 지표 추가", description = "새로운 지표를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공적으로 생성됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SoftwareMetricDTO.class)))
    })
    @PostMapping
    public ResponseEntity<SoftwareMetricDTO> createProjectMetric(
            @RequestBody SoftwareMetricCreateUpdateDTO softwareMetricCreateUpdateDTO
    ) {
        return ResponseEntity.ok(softwareMetricService.createProjectMetricDTO(softwareMetricCreateUpdateDTO));
    }

    @Operation(summary = "지표 삭제", description = "주어진 ID에 해당하는 지표를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "성공적으로 삭제됨"),
            @ApiResponse(responseCode = "404", description = "해당 ID의 지표를 찾을 수 없음")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long id) {
        softwareMetricService.deleteAsset(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "지표 수정", description = "지표을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 수정됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SoftwareMetricDTO.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<SoftwareMetricDTO> updateAsset(
            @PathVariable Long id,
            @RequestBody SoftwareMetricCreateUpdateDTO softwareMetricCreateUpdateDTO) {
        return ResponseEntity.ok(softwareMetricService.updateAsset(id, softwareMetricCreateUpdateDTO));
    }
}
