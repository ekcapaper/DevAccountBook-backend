package com.ekcapaper.software.accounting.backend.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "technical_relationships")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechnicalRelationship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "technical_context_id")
    private TechnicalContext technicalContext;

    @OneToOne
    @JoinColumn(name = "technical_decision_id")
    private TechnicalDecision technicalDecision;
}
