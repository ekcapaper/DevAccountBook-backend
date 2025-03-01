package com.ekcapaper.software.accounting.backend.converter;

import com.ekcapaper.software.accounting.backend.model.dto.TechnicalContextDecisionDTO;
import com.ekcapaper.software.accounting.backend.model.entity.TechnicalContextDecision;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TechnicalContextDecisionConverter {
    final private TechnicalDecisionConverter technicalDecisionConverter;
    final private TechnicalContextConverter technicalContextConverter;

    public TechnicalContextDecisionDTO convertToDTO(TechnicalContextDecision relationship) {
        TechnicalContextDecisionDTO dto = new TechnicalContextDecisionDTO();
        dto.setId(relationship.getId());
        dto.setTechnicalContextDTO(technicalContextConverter.convertToDTO(relationship.getTechnicalContext()));
        dto.setTechnicalDecisionDTO(technicalDecisionConverter.convertToDTO(relationship.getTechnicalDecision()));
        return dto;
    }
}
