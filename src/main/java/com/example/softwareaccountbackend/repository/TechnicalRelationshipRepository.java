package com.example.softwareaccountbackend.repository;

import com.example.softwareaccountbackend.model.entity.TechnicalRelationship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnicalRelationshipRepository extends JpaRepository<TechnicalRelationship, Long> {
    List<TechnicalRelationship> findBySourceId(Long sourceId);
    List<TechnicalRelationship> findByTargetId(Long targetId);
}
