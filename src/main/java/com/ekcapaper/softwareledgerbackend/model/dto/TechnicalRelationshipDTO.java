package com.ekcapaper.softwareledgerbackend.model.dto;

import com.ekcapaper.softwareledgerbackend.model.entity.TechnicalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TechnicalRelationshipDTO {
    private Long sourceId;
    private TechnicalType sourceType;
    private Long targetId;
    private TechnicalType targetType;
}
