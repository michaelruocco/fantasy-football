package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverterSingleton {

    private static final ObjectMapper MAPPER = createMapper();
    private static final JsonConverter CONVERTER = build(MAPPER);

    public static JsonConverter get() {
        return CONVERTER;
    }

    private static ObjectMapper createMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

    private static JsonConverter build(ObjectMapper mapper) {
        return new JsonConverter(mapper.reader(), mapper.writer(), mapper.getFactory());
    }

}
