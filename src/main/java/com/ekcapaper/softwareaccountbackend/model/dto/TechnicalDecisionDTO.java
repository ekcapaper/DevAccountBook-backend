package com.ekcapaper.softwareaccountbackend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TechnicalDecisionDTO {
    private Long id;
    private String decision;
    private String impact;
}
