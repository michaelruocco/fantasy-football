package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.io.UncheckedIOException;

public class JsonConverter {

    private final ObjectReader reader;
    private final ObjectWriter writer;
    private final JsonFactory factory;

    public JsonConverter(ObjectReader reader, ObjectWriter writer, JsonFactory factory) {
        this.reader = reader;
        this.writer = writer;
        this.factory = factory;
    }

    public String toJson(Object object) {
        try {
            return writer.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new UncheckedIOException(e);
        }
    }

    public <T> T fromJson(String json, Class<T> clazz) {
        try {
            JsonParser parser = factory.createParser(json);
            return reader.readValue(parser, clazz);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
