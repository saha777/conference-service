package com.hungrysharks.conference.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
public class ConferenceDate {
    @NotNull
    @Future
    private Date date;
}
