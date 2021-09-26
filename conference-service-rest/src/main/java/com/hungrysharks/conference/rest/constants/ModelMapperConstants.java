package com.hungrysharks.conference.rest.constants;

import com.hungrysharks.conference.rest.dto.ConferenceResponseDto;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ModelMapperConstants {
    public static final Type CONFERENCE_LIST_TYPE = new TypeToken<List<ConferenceResponseDto>>() {}.getType();
}
