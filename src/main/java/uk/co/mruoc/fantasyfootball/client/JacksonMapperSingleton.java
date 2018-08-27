package uk.co.mruoc.fantasyfootball.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonMapperSingleton {

    private static final ObjectMapper MAPPER = createMapper();

    public static ObjectMapper get() {
        return MAPPER;
    }

    private static ObjectMapper createMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

}
