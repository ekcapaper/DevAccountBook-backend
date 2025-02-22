package com.ekcapaper.softwareaccountbackend.service;

import com.ekcapaper.softwareaccountbackend.model.entity.TechnicalDecision;
import com.ekcapaper.softwareaccountbackend.repository.TechnicalDecisionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TechnicalDecisionService {

    private final TechnicalDecisionRepository decisionRepository;

    // 모든 결정 조회
    public List<TechnicalDecision> getAllDecisions() {
        return decisionRepository.findAll();
    }

    // 특정 결정 조회
    public Optional<TechnicalDecision> getDecisionById(Long id) {
        return decisionRepository.findById(id);
    }

    // 새로운 기술적 결정 추가
    public TechnicalDecision createDecision(TechnicalDecision decision) {
        return decisionRepository.save(decision);
    }

    // 기술적 결정 수정
    @Transactional
    public TechnicalDecision updateDecision(Long id, TechnicalDecision updatedDecision) {
        return decisionRepository.findById(id)
                .map(existingDecision -> {
                    existingDecision.setDecision(updatedDecision.getDecision());
                    existingDecision.setImpact(updatedDecision.getImpact());
                    return decisionRepository.save(existingDecision);
                })
                .orElseThrow(() -> new RuntimeException("Technical Decision not found"));
    }

    // 기술적 결정 삭제
    public void deleteDecision(Long id) {
        decisionRepository.deleteById(id);
    }
}