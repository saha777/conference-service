package com.hungrysharks.conference.core;

import com.hungrysharks.conference.domain.Conference;
import com.hungrysharks.conference.domain.dto.ConferenceCreationRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface ConferenceService {

    List<Conference> getAllConferences();

    UUID createConference(@Valid ConferenceCreationRequest conference);

    void updateConference(@Valid Conference conference);
}
