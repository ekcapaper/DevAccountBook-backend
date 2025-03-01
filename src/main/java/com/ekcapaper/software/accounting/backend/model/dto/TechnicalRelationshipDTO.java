package com.ekcapaper.software.accounting.backend.model.dto;

import com.ekcapaper.software.accounting.backend.model.entity.TechnicalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TechnicalRelationshipDTO {
    private Long id;
    private Long technicalContextId;
    private Long technicalDecisionId;
}
