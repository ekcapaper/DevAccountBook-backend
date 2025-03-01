package com.ekcapaper.software.accounting.backend.service;

import com.ekcapaper.software.accounting.backend.model.dto.TechnicalContextDTO;
import com.ekcapaper.software.accounting.backend.model.entity.TechnicalContext;
import com.ekcapaper.software.accounting.backend.repository.TechnicalContextRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TechnicalContextService {

    private final TechnicalContextRepository contextRepository;

    // 모든 기술적 상황 조회 (DTO 변환)
    public List<TechnicalContextDTO> getAllContexts() {
        return contextRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // 특정 기술적 상황 조회 (DTO 변환)
    public Optional<TechnicalContextDTO> getContextById(Long id) {
        return contextRepository.findById(id).map(this::convertToDTO);
    }

    // 새로운 기술적 상황 추가
    public TechnicalContextDTO createContext(TechnicalContext context) {
        return convertToDTO(contextRepository.save(context));
    }

    // 기술적 상황 수정
    @Transactional
    public TechnicalContextDTO updateContext(Long id, TechnicalContext updatedContext) {
        return contextRepository.findById(id)
                .map(existingContext -> {
                    existingContext.setName(updatedContext.getName());
                    existingContext.setDescription(updatedContext.getDescription());
                    return convertToDTO(contextRepository.save(existingContext));
                })
                .orElseThrow(() -> new RuntimeException("Technical Context not found"));
    }

    // 기술적 상황 삭제
    public void deleteContext(Long id) {
        contextRepository.deleteById(id);
    }

    // 엔티티 → DTO 변환 메서드
    private TechnicalContextDTO convertToDTO(TechnicalContext context) {
        TechnicalContextDTO dto = new TechnicalContextDTO();
        dto.setId(context.getId());
        dto.setName(context.getName());
        dto.setDescription(context.getDescription());
        return dto;
    }
}
