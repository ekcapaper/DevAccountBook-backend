package com.ekcapaper.softwareaccountbackend.controller;

import com.ekcapaper.softwareaccountbackend.model.entity.SoftwareAsset;
import com.ekcapaper.softwareaccountbackend.service.SoftwareAssetService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/assets")
@RequiredArgsConstructor
public class SoftwareAssetController {

    private final SoftwareAssetService assetService;

    @Operation(summary = "모든 소프트웨어 자산 조회", description = "등록된 모든 소프트웨어 자산을 반환합니다.")
    @GetMapping
    public ResponseEntity<List<SoftwareAsset>> getAllAssets() {
        return ResponseEntity.ok(assetService.getAllAssets());
    }

    @Operation(summary = "특정 소프트웨어 자산 조회", description = "주어진 ID에 해당하는 소프트웨어 자산을 반환합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<SoftwareAsset> getAssetById(@PathVariable Long id) {
        Optional<SoftwareAsset> asset = assetService.getAssetById(id);
        return asset.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "새로운 소프트웨어 자산 추가", description = "새로운 소프트웨어 자산을 등록합니다.")
    @PostMapping
    public ResponseEntity<SoftwareAsset> createAsset(@RequestBody SoftwareAsset asset) {
        return ResponseEntity.ok(assetService.createAsset(asset));
    }
}
