package com.hungrysharks.conference.rest.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
public class ConferenceDateDto {
    @NotNull
    @Future
    private Date date;
}
