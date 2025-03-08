package com.ekcapaper.software.accounting.backend.controller;

import com.ekcapaper.software.accounting.backend.model.dto.*;
import com.ekcapaper.software.accounting.backend.model.entity.ProjectMetric;
import com.ekcapaper.software.accounting.backend.model.entity.TechnicalDecision;
import com.ekcapaper.software.accounting.backend.service.ProjectMetricService;
import com.ekcapaper.software.accounting.backend.service.SoftwareAssetService;
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
@RequestMapping("/api/project/metric")
@RequiredArgsConstructor
public class ProjectMetricController {

    private final ProjectMetricService projectMetricService;

    @Operation(summary = "지표 조회", description = "등록된 모든 소프트웨어 지표을 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProjectMetricDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<ProjectMetricDTO>> getAllProjectMetrics() {
        return ResponseEntity.ok(projectMetricService.getAllProjectMetrics());
    }

    @Operation(summary = "특정 지표 조회", description = "주어진 ID에 해당하는 지표을 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProjectMetricDTO.class))),
            @ApiResponse(responseCode = "404", description = "해당 ID의 지표를 찾을 수 없음")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProjectMetricDTO> getProjectMetricsById(@PathVariable Long id) {
        return projectMetricService.getProjectMetricById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "새로운 지표 추가", description = "새로운 지표를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공적으로 생성됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProjectMetricDTO.class)))
    })
    @PostMapping
    public ResponseEntity<ProjectMetricDTO> createProjectMetric(
            @RequestBody ProjectMetricCreateDTO projectMetricCreateDTO
    ) {
        return ResponseEntity.ok(projectMetricService.createProjectMetricDTO(projectMetricCreateDTO));
    }

    @Operation(summary = "지표 삭제", description = "주어진 ID에 해당하는 지표를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "성공적으로 삭제됨"),
            @ApiResponse(responseCode = "404", description = "해당 ID의 지표를 찾을 수 없음")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long id) {
        projectMetricService.deleteAsset(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "지표 수정", description = "지표을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 수정됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProjectMetricDTO.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProjectMetricDTO> updateAsset(
            @PathVariable Long id,
            @RequestBody ProjectMetricCreateDTO projectMetricCreateDTO) {
        return ResponseEntity.ok(projectMetricService.updateAsset(id, projectMetricCreateDTO));
    }
}
