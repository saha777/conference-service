package com.hungrysharks.conference.dao.repository;

import com.hungrysharks.conference.dao.entities.ConferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ConferenceRepository extends JpaRepository<ConferenceEntity, UUID> {
    Optional<ConferenceEntity> findByName(String name);
}
