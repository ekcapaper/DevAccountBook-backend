package com.ekcapaper.software.accounting.backend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SoftwareEquityDTO {
    private Long id;
    private String name;
    private String description;
}