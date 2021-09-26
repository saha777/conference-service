package com.hungrysharks.conference.rest;

import com.hungrysharks.conference.core.ConferenceService;
import com.hungrysharks.conference.domain.Conference;
import com.hungrysharks.conference.domain.dto.ConferenceCreationRequest;
import com.hungrysharks.conference.rest.dto.ConferenceChangeRequestDto;
import com.hungrysharks.conference.rest.dto.ConferenceCreationResponseDto;
import com.hungrysharks.conference.rest.dto.ConferenceResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

import static com.hungrysharks.conference.rest.constants.ModelMapperConstants.CONFERENCE_LIST_TYPE;

@Slf4j
@Validated
@RestController
@RequestMapping("/conferences")
@RequiredArgsConstructor
public class ConferenceController {

    private final ModelMapper modelMapper;
    private final ConferenceService conferenceService;

    @GetMapping
    public ResponseEntity<List<ConferenceResponseDto>> getAllConferences() {
        return ResponseEntity.ok(modelMapper.map(conferenceService.getAllConferences(), CONFERENCE_LIST_TYPE));
    }

    @PostMapping
    public ResponseEntity<ConferenceCreationResponseDto> createConference(@RequestBody @Valid ConferenceChangeRequestDto request) {
        UUID id = conferenceService.createConference(modelMapper.map(request, ConferenceCreationRequest.class));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ConferenceCreationResponseDto(id));
    }

    @PutMapping("/{conferenceId}")
    public ResponseEntity updateConference(@PathVariable @NotNull UUID conferenceId,
                                           @RequestBody @Valid ConferenceChangeRequestDto request) {
        var conference = modelMapper.map(request, Conference.class);
        conference.setId(conferenceId);
        conferenceService.updateConference(conference);
        return ResponseEntity.noContent().build();
    }

}
