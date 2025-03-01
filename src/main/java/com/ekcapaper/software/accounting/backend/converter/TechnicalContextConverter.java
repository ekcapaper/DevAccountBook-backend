package com.ekcapaper.software.accounting.backend.converter;

import com.ekcapaper.software.accounting.backend.model.dto.TechnicalContextDTO;
import com.ekcapaper.software.accounting.backend.model.entity.TechnicalContext;
import org.springframework.stereotype.Component;

@Component
public class TechnicalContextConverter {
    public TechnicalContextDTO convertToDTO(TechnicalContext technicalContext) {
        TechnicalContextDTO dto = new TechnicalContextDTO();
        dto.setId(technicalContext.getId());
        dto.setName(technicalContext.getName());
        dto.setDescription(technicalContext.getDescription());
        return dto;
    }

}
