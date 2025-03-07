package com.ekcapaper.software.accounting.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "technical_decisions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechnicalDecision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;  // 의사결정 내용

    @Column(columnDefinition = "TEXT")
    private String description;  // 결정이 미친 영향
}
