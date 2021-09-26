package com.hungrysharks.conference.rest.dto;

import com.hungrysharks.conference.domain.ConferenceDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ToString
public class ConferenceChangeRequestDto {
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
