package com.hungrysharks.conference.dao.repository;

import com.hungrysharks.conference.dao.entities.ConferenceDateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConferenceDateRepository extends JpaRepository<ConferenceDateEntity, UUID> {
    Optional<ConferenceDateEntity> findFirstByDateIn(List<Date> dates);
}
