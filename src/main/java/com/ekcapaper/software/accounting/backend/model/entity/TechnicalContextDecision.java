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
public class TechnicalContextDecision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "technical_context_id", nullable = false) // ✅ 수정된 부분
    private TechnicalContext technicalContext;

    @OneToOne
    @JoinColumn(name = "technical_decision_id", nullable = false) // ✅ 수정된 부분
    private TechnicalDecision technicalDecision;
}
