package com.magement.gateway.common;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public final class Utilities {
    public static ModelMapper getModelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }
}
