package com.example.softwareaccountbackend.model.entity;

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
    private String decision;  // 의사결정 내용

    @Column(columnDefinition = "TEXT")
    private String impact;  // 결정이 미친 영향
}
