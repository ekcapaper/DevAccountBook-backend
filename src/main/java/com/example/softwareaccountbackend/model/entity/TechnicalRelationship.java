package com.example.softwareaccountbackend.model.entity;

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

    @Column(nullable = false)
    private Long sourceId;  // 원인 (상황 or 결정)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TechnicalType sourceType;  // CONTEXT or DECISION

    @Column(nullable = false)
    private Long targetId;  // 결과 (상황 or 결정)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TechnicalType targetType;  // CONTEXT or DECISION

}
