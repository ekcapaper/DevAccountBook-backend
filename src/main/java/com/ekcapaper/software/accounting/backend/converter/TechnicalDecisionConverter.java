package com.ekcapaper.software.accounting.backend.converter;

import com.ekcapaper.software.accounting.backend.model.dto.TechnicalDecisionDTO;
import com.ekcapaper.software.accounting.backend.model.entity.TechnicalDecision;
import org.springframework.stereotype.Component;

@Component
public class TechnicalDecisionConverter {
    public TechnicalDecisionDTO convertToDTO(TechnicalDecision decision) {
        TechnicalDecisionDTO dto = new TechnicalDecisionDTO();
        dto.setId(decision.getId());
        dto.setName(decision.getName());
        dto.setDescription(decision.getDescription());
        return dto;
    }
}
