package com.ekcapaper.software.accounting.backend.controller;

import com.ekcapaper.software.accounting.backend.model.dto.TechnicalContextCreateDTO;
import com.ekcapaper.software.accounting.backend.model.dto.TechnicalContextDTO;
import com.ekcapaper.software.accounting.backend.model.entity.TechnicalContext;
import com.ekcapaper.software.accounting.backend.service.TechnicalContextService;
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
@RequestMapping("/api/technical/contexts")
@RequiredArgsConstructor
public class TechnicalContextController {
    private final TechnicalContextService contextService;

    @Operation(summary = "모든 기술적 상황 조회", description = "등록된 모든 기술적 상황(Context)를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TechnicalContextDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<TechnicalContextDTO>> getAllTechnicalContexts() {
        return ResponseEntity.ok(contextService.getAllContexts());
    }

    @Operation(summary = "특정 기술적 상황 조회", description = "주어진 ID에 해당하는 기술적 상황을 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 조회됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TechnicalContextDTO.class))),
            @ApiResponse(responseCode = "404", description = "해당 ID의 기술적 상황을 찾을 수 없음")
    })
    @GetMapping("/{id}")
    public ResponseEntity<TechnicalContextDTO> getTechnicalContextById(@PathVariable Long id) {
        Optional<TechnicalContextDTO> context = contextService.getContextById(id);
        return context.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "새로운 기술적 상황 추가", description = "새로운 기술적 상황을 등록합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "성공적으로 생성됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TechnicalContextDTO.class)))
    })
    @PostMapping
    public ResponseEntity<TechnicalContextDTO> createTechnicalContext(
            @RequestBody TechnicalContextCreateDTO contextDTO) {
        TechnicalContext context = new TechnicalContext();
        context.setName(contextDTO.getName());
        context.setDescription(contextDTO.getDescription());
        return ResponseEntity.ok(contextService.createContext(context));
    }

    @Operation(summary = "기술적 상황 수정", description = "기술적 상황을 수정합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공적으로 수정됨",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TechnicalContextDTO.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<TechnicalContextDTO> updateTechnicalContext(
            @PathVariable Long id,
            @RequestBody TechnicalContextDTO contextDTO) {
        TechnicalContext context = new TechnicalContext();
        context.setName(contextDTO.getName());
        context.setDescription(contextDTO.getDescription());
        return ResponseEntity.ok(contextService.updateContext(id, context));
    }

    @Operation(summary = "기술적 상황 삭제", description = "기술적 상황을 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "성공적으로 삭제됨")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTechnicalContext(@PathVariable Long id) {
        contextService.deleteContext(id);
        return ResponseEntity.noContent().build();
    }
}
