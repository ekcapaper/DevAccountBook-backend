package com.ekcapaper.software.accounting.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoftwareLiabilityCreateDTO {
    private String name;
    private String description;
}
