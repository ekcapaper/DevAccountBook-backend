package com.ekcapaper.softwareledgerbackend.controller;

import com.ekcapaper.softwareledgerbackend.model.entity.SoftwareEquity;
import com.ekcapaper.softwareledgerbackend.service.SoftwareEquityService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/equities")
@RequiredArgsConstructor
public class SoftwareEquityController {

    private final SoftwareEquityService equityService;

    @Operation(summary = "모든 소프트웨어 핵심 기술 조회", description = "등록된 모든 소프트웨어 핵심 기술을 반환합니다.")
    @GetMapping
    public ResponseEntity<List<SoftwareEquity>> getAllEquities() {
        return ResponseEntity.ok(equityService.getAllEquities());
    }

    @Operation(summary = "특정 소프트웨어 핵심 기술 조회", description = "주어진 ID에 해당하는 소프트웨어 핵심 기술을 반환합니다.")
    @GetMapping("/{id}")
    public ResponseEntity<SoftwareEquity> getEquityById(@PathVariable Long id) {
        Optional<SoftwareEquity> equity = equityService.getEquityById(id);
        return equity.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "새로운 소프트웨어 핵심 기술 추가", description = "새로운 소프트웨어 핵심 기술을 등록합니다.")
    @PostMapping
    public ResponseEntity<SoftwareEquity> createEquity(@RequestBody SoftwareEquity equity) {
        return ResponseEntity.ok(equityService.createEquity(equity));
    }
}
