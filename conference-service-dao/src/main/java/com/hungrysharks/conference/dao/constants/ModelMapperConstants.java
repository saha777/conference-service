package com.hungrysharks.conference.dao.constants;

import com.hungrysharks.conference.domain.Conference;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ModelMapperConstants {
    public static final Type CONFERENCE_LIST_TYPE = new TypeToken<List<Conference>>() {}.getType();
}
