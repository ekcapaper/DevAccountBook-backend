package com.ekcapaper.softwareaccountbackend.service;

import com.ekcapaper.softwareaccountbackend.model.entity.SoftwareEquity;
import com.ekcapaper.softwareaccountbackend.repository.SoftwareEquityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SoftwareEquityService {

    private final SoftwareEquityRepository equityRepository;

    /**
     * 모든 소프트웨어 핵심 기술을 조회합니다.
     */
    public List<SoftwareEquity> getAllEquities() {
        return equityRepository.findAll();
    }

    /**
     * ID로 특정 소프트웨어 핵심 기술을 조회합니다.
     */
    public Optional<SoftwareEquity> getEquityById(Long id) {
        return equityRepository.findById(id);
    }

    /**
     * 새로운 소프트웨어 핵심 기술을 추가합니다.
     */
    public SoftwareEquity createEquity(SoftwareEquity equity) {
        return equityRepository.save(equity);
    }

    /**
     * ID를 기반으로 소프트웨어 핵심 기술을 삭제합니다.
     */
    public void deleteEquity(Long id) {
        equityRepository.deleteById(id);
    }
}
