package com.hungrysharks.conference.dao;

import com.hungrysharks.conference.dao.entities.ConferenceDateEntity;
import com.hungrysharks.conference.dao.entities.ConferenceEntity;
import com.hungrysharks.conference.dao.repository.ConferenceDateRepository;
import com.hungrysharks.conference.dao.repository.ConferenceRepository;
import com.hungrysharks.conference.domain.Conference;
import com.hungrysharks.conference.domain.ConferenceDate;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.hungrysharks.conference.dao.constants.ModelMapperConstants.CONFERENCE_LIST_TYPE;

@Validated
@Component
@RequiredArgsConstructor
public class ConferenceDaoImpl implements ConferenceDao {

    private final ModelMapper modelMapper;
    private final ConferenceRepository conferenceRepository;
    private final ConferenceDateRepository conferenceDateRepository;

    @Override
    public List<Conference> findAll() {
        return modelMapper.map(conferenceRepository.findAll(), CONFERENCE_LIST_TYPE);
    }

    @Override
    public Optional<Conference> findById(@NotNull UUID id) {
        return conferenceRepository.findById(id)
                .map(this::map);
    }

    @Override
    public Optional<Conference> findByName(@NotBlank String name) {
        return conferenceRepository.findByName(name)
                .map(this::map);
    }

    @Override
    public Optional<Conference> findFirstByAnyOfDates(@NotEmpty List<Date> dates) {
        return conferenceDateRepository.findFirstByDateIn(dates)
                .map(ConferenceDateEntity::getConference)
                .map(this::map);
    }

    /**
     * Make update if conference has id and id is present in database or create otherwise
     *
     * @param conference conference domain model
     * @return saved conference
     */
    @Override
    @Transactional
    public Conference save(@NotNull Conference conference) {
        ConferenceEntity conferenceEntity = Optional.ofNullable(conference.getId())
                .flatMap(
                        id -> conferenceRepository.findById(id)
                                .map(entity -> updateEntity(entity, conference))
                ).orElse(createEntity(conference));

        return map(conferenceRepository.save(conferenceEntity));
    }

    private ConferenceEntity createEntity(Conference domain) {
        ConferenceEntity entity = new ConferenceEntity();
        entity.setName(domain.getName());
        entity.setSubject(domain.getSubject());
        entity.setParticipantCount(domain.getParticipantCount());
        entity.setDates(domain.getDates().stream()
                .map(d -> new ConferenceDateEntity(d.getDate(), entity))
                .collect(Collectors.toList()));
        return entity;
    }

    private ConferenceEntity updateEntity(ConferenceEntity entity, Conference domain) {
        entity.setName(domain.getName());
        entity.setSubject(domain.getSubject());
        entity.setParticipantCount(domain.getParticipantCount());

        var domainDates = domain.getDates().stream()
                .map(ConferenceDate::getDate)
                .collect(Collectors.toSet());
        var newDates = entity.getDates().stream()
                .filter(confDate -> {
                    boolean contains = domainDates.contains(confDate.getDate());
                    if (contains) {
                        domainDates.remove(confDate.getDate());
                    }
                    return contains;
                }).collect(Collectors.toList());
        newDates.addAll(domainDates.stream()
                .map(date -> new ConferenceDateEntity(date, entity))
                .collect(Collectors.toList()));

        return entity;
    }

    private Conference map(ConferenceEntity entity) {
        return modelMapper.map(entity, Conference.class);
    }
}
