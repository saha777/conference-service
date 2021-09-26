package com.hungrysharks.conference.rest.dto;

import com.hungrysharks.conference.domain.ConferenceDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class ConferenceResponseDto {
    private UUID id;
    private String name;
    private String subject;
    private List<ConferenceDate> dates;
    private Integer participantCount;
}
