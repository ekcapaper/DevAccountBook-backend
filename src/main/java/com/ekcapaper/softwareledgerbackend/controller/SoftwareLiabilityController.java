package com.ekcapaper.softwareledgerbackend.controller;

import com.ekcapaper.softwareledgerbackend.model.dto.SoftwareLiabilityDTO;
import com.ekcapaper.softwareledgerbackend.model.entity.SoftwareLiability;
import com.ekcapaper.softwareledgerbackend.service.SoftwareLiabilityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/liabilities")
@RequiredArgsConstructor
public class SoftwareLiabilityController {

    private final SoftwareLiabilityService liabilityService;

    @Operation(summary = "모든 소프트웨어 부채 조회", description = "등록된 모든 소프트웨어 부채를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SoftwareLiabilityDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<SoftwareLiabilityDTO>> getAllLiabilities() {
        return ResponseEntity.ok(liabilityService.getAllLiabilities());
    }

    @Operation(summary = "특정 소프트웨어 부채 조회", description = "주어진 ID에 해당하는 소프트웨어 부채를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SoftwareLiabilityDTO.class))),
            @ApiResponse(responseCode = "404", description = "해당 ID의 소프트웨어 부채를 찾을 수 없음")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SoftwareLiabilityDTO> getLiabilityById(@PathVariable Long id) {
        return liabilityService.getLiabilityById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "새로운 소프트웨어 부채 추가", description = "새로운 소프트웨어 부채를 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공적으로 생성됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = SoftwareLiabilityDTO.class)))
    })
    @PostMapping
    public ResponseEntity<SoftwareLiabilityDTO> createLiability(
            @RequestBody SoftwareLiabilityDTO liabilityDTO) {
        return ResponseEntity.ok(liabilityService.createLiability(liabilityDTO));
    }

    @Operation(summary = "소프트웨어 부채 삭제", description = "주어진 ID에 해당하는 소프트웨어 부채를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "성공적으로 삭제됨"),
            @ApiResponse(responseCode = "404", description = "해당 ID의 소프트웨어 부채를 찾을 수 없음")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLiability(@PathVariable Long id) {
        liabilityService.deleteLiability(id);
        return ResponseEntity.noContent().build();
    }
}
