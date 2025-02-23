package com.ekcapaper.softwareledgerbackend.service;

import com.ekcapaper.softwareledgerbackend.model.entity.SoftwareLiability;
import com.ekcapaper.softwareledgerbackend.repository.SoftwareLiabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SoftwareLiabilityService {

    private final SoftwareLiabilityRepository liabilityRepository;

    /**
     * 모든 소프트웨어 부채를 조회합니다.
     */
    public List<SoftwareLiability> getAllLiabilities() {
        return liabilityRepository.findAll();
    }

    /**
     * ID로 특정 소프트웨어 부채를 조회합니다.
     */
    public Optional<SoftwareLiability> getLiabilityById(Long id) {
        return liabilityRepository.findById(id);
    }

    /**
     * 새로운 소프트웨어 부채를 추가합니다.
     */
    public SoftwareLiability createLiability(SoftwareLiability liability) {
        return liabilityRepository.save(liability);
    }

    /**
     * ID를 기반으로 소프트웨어 부채를 삭제합니다.
     */
    public void deleteLiability(Long id) {
        liabilityRepository.deleteById(id);
    }
}
