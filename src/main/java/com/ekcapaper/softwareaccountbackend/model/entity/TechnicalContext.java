package com.ekcapaper.softwareaccountbackend.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "technical_contexts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechnicalContext {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;  // 상황 제목

    @Column(columnDefinition = "TEXT")
    private String description;  // 상세 설명

}
