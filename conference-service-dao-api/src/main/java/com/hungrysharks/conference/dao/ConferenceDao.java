package com.hungrysharks.conference.dao;

import com.hungrysharks.conference.domain.Conference;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConferenceDao {
    List<Conference> findAll();

    Optional<Conference> findById(@NotNull UUID id);

    Optional<Conference> findByName(@NotBlank String name);

    Optional<Conference> findFirstByAnyOfDates(@NotEmpty List<Date> dates);

    Conference save(@NotNull Conference conference);
}
