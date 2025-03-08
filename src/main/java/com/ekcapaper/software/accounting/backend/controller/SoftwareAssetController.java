package com.ekcapaper.software.accounting.backend.controller;

import com.ekcapaper.software.accounting.backend.model.dto.SoftwareAssetCreateUpdateDTO;
import com.ekcapaper.software.accounting.backend.model.dto.SoftwareAssetDTO;
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
@RequestMapping("/api/software/assets")
@RequiredArgsConstructor
public class SoftwareAssetController {

    private final SoftwareAssetService assetService;

    @Operation(summary = "모든 소프트웨어 자산 조회", description = "등록된 모든 소프트웨어 자산을 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SoftwareAssetDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<SoftwareAssetDTO>> getAllAssets() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

    @Operation(summary = "특정 소프트웨어 자산 조회", description = "주어진 ID에 해당하는 소프트웨어 자산을 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SoftwareAssetDTO.class))),
            @ApiResponse(responseCode = "404", description = "해당 ID의 소프트웨어 자산을 찾을 수 없음")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SoftwareAssetDTO> getAssetById(@PathVariable Long id) {
        return assetService.getAssetById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "새로운 소프트웨어 자산 추가", description = "새로운 소프트웨어 자산을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공적으로 생성됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SoftwareAssetDTO.class)))
    })
    @PostMapping
    public ResponseEntity<SoftwareAssetDTO> createAsset(
            @RequestBody SoftwareAssetCreateUpdateDTO assetDTO) {
        return ResponseEntity.ok(assetService.createAsset(assetDTO));
    }

    @Operation(summary = "소프트웨어 자산 삭제", description = "주어진 ID에 해당하는 소프트웨어 자산을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "성공적으로 삭제됨"),
            @ApiResponse(responseCode = "404", description = "해당 ID의 소프트웨어 자산을 찾을 수 없음")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Long id) {
        assetService.deleteAsset(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "소프트웨어 자산 수정", description = "소프트웨어 자산을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 수정됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SoftwareAssetDTO.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<SoftwareAssetDTO> updateAsset(
            @PathVariable Long id,
            @RequestBody SoftwareAssetCreateUpdateDTO assetDTO) {
        return ResponseEntity.ok(assetService.updateAsset(id, assetDTO));
    }
}