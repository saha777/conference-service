package com.hungrysharks.conference.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@ToString
public class Conference {
    @NotNull
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String subject;

    @NotEmpty
    @Valid
    private List<ConferenceDate> dates;

    @NotNull
    @Min(101)
    private Integer participantCount;
}
