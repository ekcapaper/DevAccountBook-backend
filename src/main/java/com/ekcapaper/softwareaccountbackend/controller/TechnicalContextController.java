package com.ekcapaper.softwareaccountbackend.controller;

import com.ekcapaper.softwareaccountbackend.model.dto.TechnicalContextDTO;
import com.ekcapaper.softwareaccountbackend.model.entity.TechnicalContext;
import com.ekcapaper.softwareaccountbackend.service.TechnicalContextService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contexts")
@RequiredArgsConstructor
public class TechnicalContextController {

    private final TechnicalContextService contextService;

    // 모든 기술적 상황 조회
    @GetMapping
    public ResponseEntity<List<TechnicalContextDTO>> getAllContexts() {
        return ResponseEntity.ok(contextService.getAllContexts());
    }

    // 특정 기술적 상황 조회
    @GetMapping("/{id}")
    public ResponseEntity<TechnicalContextDTO> getContextById(@PathVariable Long id) {
        Optional<TechnicalContextDTO> context = contextService.getContextById(id);
        return context.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 새로운 기술적 상황 추가
    @PostMapping
    public ResponseEntity<TechnicalContextDTO> createContext(@RequestBody TechnicalContextDTO contextDTO) {
        TechnicalContext context = new TechnicalContext();
        context.setName(contextDTO.getName());
        context.setDescription(contextDTO.getDescription());
        return ResponseEntity.ok(contextService.createContext(context));
    }

    // 기술적 상황 수정
    @PutMapping("/{id}")
    public ResponseEntity<TechnicalContextDTO> updateContext(@PathVariable Long id, @RequestBody TechnicalContextDTO contextDTO) {
        TechnicalContext context = new TechnicalContext();
        context.setName(contextDTO.getName());
        context.setDescription(contextDTO.getDescription());
        return ResponseEntity.ok(contextService.updateContext(id, context));
    }

    // 기술적 상황 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContext(@PathVariable Long id) {
        contextService.deleteContext(id);
        return ResponseEntity.noContent().build();
    }
}
