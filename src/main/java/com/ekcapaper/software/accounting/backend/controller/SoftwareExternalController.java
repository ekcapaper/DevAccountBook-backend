package com.ekcapaper.software.accounting.backend.controller;

import com.ekcapaper.software.accounting.backend.model.dto.SoftwareAssetCreateUpdateDTO;
import com.ekcapaper.software.accounting.backend.model.dto.SoftwareAssetDTO;
import com.ekcapaper.software.accounting.backend.model.dto.SoftwareExternalCreateUpdateDTO;
import com.ekcapaper.software.accounting.backend.model.dto.SoftwareExternalDTO;
import com.ekcapaper.software.accounting.backend.service.SoftwareAssetService;
import com.ekcapaper.software.accounting.backend.service.SoftwareExternalService;
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
@RequestMapping("/api/software/externals")
@RequiredArgsConstructor
public class SoftwareExternalController {

    private final SoftwareExternalService softwareExternalService;

    @Operation(summary = "모든 소프트웨어 외부 조회", description = "등록된 모든 소프트웨어 외부 자료를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SoftwareExternalDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<SoftwareExternalDTO>> getAllExternals() {
        return ResponseEntity.ok(softwareExternalService.getAllSoftwareExternals());
    }

    @Operation(summary = "특정 소프트웨어 외부 자료 조회", description = "주어진 ID에 해당하는 소프트웨어 외부 자료를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SoftwareExternalDTO.class))),
            @ApiResponse(responseCode = "404", description = "해당 ID의 소프트웨어 외부 자료를 찾을 수 없음")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SoftwareExternalDTO> getExternalById(@PathVariable Long id) {
        return softwareExternalService.getSoftwareExternalById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "새로운 소프트웨어 외부 자료 추가", description = "새로운 소프트웨어 외부 자료을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공적으로 생성됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SoftwareExternalDTO.class)))
    })
    @PostMapping
    public ResponseEntity<SoftwareExternalDTO> createExternal(
            @RequestBody SoftwareExternalCreateUpdateDTO softwareExternalCreateUpdateDTO) {
        return ResponseEntity.ok(softwareExternalService.createSoftwareExternal(softwareExternalCreateUpdateDTO));
    }

    @Operation(summary = "소프트웨어 외부 자료 삭제", description = "주어진 ID에 해당하는 소프트웨어 외부 자료을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "성공적으로 삭제됨"),
            @ApiResponse(responseCode = "404", description = "해당 ID의 소프트웨어 외부 자료을 찾을 수 없음")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExternal(@PathVariable Long id) {
        softwareExternalService.deleteSoftwareExternal(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "소프트웨어 외부 자료 수정", description = "소프트웨어 외부 자료를 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 수정됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SoftwareAssetDTO.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<SoftwareExternalDTO> updateExternal(
            @PathVariable Long id,
            @RequestBody SoftwareExternalCreateUpdateDTO softwareExternalCreateUpdateDTO) {
        return ResponseEntity.ok(softwareExternalService.updateSoftwareExternal(id, softwareExternalCreateUpdateDTO));
    }
}