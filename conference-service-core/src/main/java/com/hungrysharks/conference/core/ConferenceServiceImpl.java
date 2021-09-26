package com.hungrysharks.conference.core;

import com.hungrysharks.conference.core.exceptions.ConferenceDateIntersectionException;
import com.hungrysharks.conference.core.exceptions.ConferenceNameDuplicateException;
import com.hungrysharks.conference.core.exceptions.ConferenceNotFoundException;
import com.hungrysharks.conference.dao.ConferenceDao;
import com.hungrysharks.conference.domain.Conference;
import com.hungrysharks.conference.domain.ConferenceDate;
import com.hungrysharks.conference.domain.dto.ConferenceCreationRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Validated
@RequiredArgsConstructor
public class ConferenceServiceImpl implements ConferenceService {

    private final ModelMapper modelMapper;
    private final ConferenceDao conferenceDao;

    @Override
    public List<Conference> getAllConferences() {
        return conferenceDao.findAll();
    }

    @Override
    @Transactional
    public UUID createConference(@Valid ConferenceCreationRequest creationRequest) {
        validateConferenceCreation(creationRequest);

        Conference conference = modelMapper.map(creationRequest, Conference.class);

        return conferenceDao.save(conference).getId();
    }

    @Override
    @Transactional
    public void updateConference(@Valid Conference conference) {
        validateConferenceUpdate(conference);

        conferenceDao.save(conference);
    }

    private void validateConferenceCreation(ConferenceCreationRequest creationRequest) {
        conferenceDao.findByName(creationRequest.getName())
                .ifPresent(c -> {throw new ConferenceNameDuplicateException();});
        conferenceDao.findFirstByAnyOfDates(getDates(creationRequest.getDates()))
                .ifPresent(c -> {throw new ConferenceDateIntersectionException();});
    }

    private void validateConferenceUpdate(Conference conference) {
        conferenceDao.findById(conference.getId())
                .orElseThrow(ConferenceNotFoundException::new);
        conferenceDao.findByName(conference.getName())
                .filter(c -> !c.getId().equals(conference.getId()))
                .ifPresent(c -> {throw new ConferenceNameDuplicateException();});
        conferenceDao.findFirstByAnyOfDates(getDates(conference.getDates()))
                .filter(c -> !c.getId().equals(conference.getId()))
                .ifPresent(c -> {throw new ConferenceDateIntersectionException();});
    }

    private List<Date> getDates(List<ConferenceDate> conferenceDates) {
        return conferenceDates.stream()
                .map(ConferenceDate::getDate)
                .collect(Collectors.toList());
    }
}
