package com.ekcapaper.software.accounting.backend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TechnicalContextDecisionDTO {
    private Long id;
    private TechnicalContextDTO technicalContextDTO;
    private TechnicalDecisionDTO technicalDecisionDTO;
}
