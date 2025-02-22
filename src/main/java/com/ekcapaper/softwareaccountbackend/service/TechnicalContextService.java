package com.ekcapaper.softwareaccountbackend.service;

import com.ekcapaper.softwareaccountbackend.model.entity.TechnicalContext;
import com.ekcapaper.softwareaccountbackend.repository.TechnicalContextRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TechnicalContextService {

    private final TechnicalContextRepository contextRepository;

    // 모든 상황 조회
    public List<TechnicalContext> getAllContexts() {
        return contextRepository.findAll();
    }

    // 특정 상황 조회
    public Optional<TechnicalContext> getContextById(Long id) {
        return contextRepository.findById(id);
    }

    // 새로운 기술적 상황 추가
    public TechnicalContext createContext(TechnicalContext context) {
        return contextRepository.save(context);
    }

    // 기술적 상황 수정
    @Transactional
    public TechnicalContext updateContext(Long id, TechnicalContext updatedContext) {
        return contextRepository.findById(id)
                .map(existingContext -> {
                    existingContext.setName(updatedContext.getName());
                    existingContext.setDescription(updatedContext.getDescription());
                    return contextRepository.save(existingContext);
                })
                .orElseThrow(() -> new RuntimeException("Technical Context not found"));
    }

    // 기술적 상황 삭제
    public void deleteContext(Long id) {
        contextRepository.deleteById(id);
    }
}
