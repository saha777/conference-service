package com.hungrysharks.conference.rest.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConferenceCreationResponseDto {
    private UUID id;
}
