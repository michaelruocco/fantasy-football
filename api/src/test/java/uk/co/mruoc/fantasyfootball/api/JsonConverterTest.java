package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;

import java.io.IOException;
import java.io.UncheckedIOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class JsonConverterTest {

    private static final String BEAN_JSON = "{\"value1\":\"my first value\",\"value2\":null}";
    private static final FakeBean BEAN = buildFakeBean();

    private final ObjectReader reader = mock(ObjectReader.class);
    private final ObjectWriter writer = mock(ObjectWriter.class);
    private final JsonFactory factory = mock(JsonFactory.class);

    private final JsonConverter converter = new JsonConverter(reader, writer, factory);

    @Test
    public void shouldConvertObjectToJson() throws JsonProcessingException {
        given(writer.writeValueAsString(BEAN)).willReturn(BEAN_JSON);

        final String json = converter.toJson(BEAN);

        assertThat(json).isEqualTo(BEAN_JSON);
    }

    @Test
    public void shouldThrowClientExceptionIfSerializationErrors() throws IOException {
        given(writer.writeValueAsString(BEAN)).willThrow(JsonProcessingException.class);

        final Throwable thrown = catchThrowable(() -> converter.toJson(BEAN));

        assertThat(thrown)
                .isInstanceOf(UncheckedIOException.class)
                .hasCauseInstanceOf(JsonProcessingException.class);
    }

    @Test
    public void shouldConvertObjectFromJson() throws IOException {
        final JsonParser parser = mock(JsonParser.class);
        given(factory.createParser(BEAN_JSON)).willReturn(parser);
        given(reader.readValue(parser, FakeBean.class)).willReturn(BEAN);

        final FakeBean bean = converter.fromJson(BEAN_JSON, FakeBean.class);

        assertThat(bean).isEqualToComparingFieldByField(bean);
    }

    @Test
    public void shouldThrowClientExceptionIfDeserializationFails() throws IOException {
        final JsonParser parser = mock(JsonParser.class);
        given(factory.createParser(BEAN_JSON)).willReturn(parser);
        given(reader.readValue(parser, FakeBean.class)).willThrow(IOException.class);

        final Throwable thrown = catchThrowable(() -> converter.fromJson(BEAN_JSON, FakeBean.class));

        assertThat(thrown)
                .isInstanceOf(UncheckedIOException.class)
                .hasCauseInstanceOf(IOException.class);
    }

    private static FakeBean buildFakeBean() {
        final FakeBean bean = new FakeBean();
        bean.setValue1("my first value");
        return bean;
    }

    private static class FakeBean {

        private String value1;
        private String value2;

        public String getValue1() {
            return value1;
        }

        public void setValue1(String value1) {
            this.value1 = value1;
        }

        public String getValue2() {
            return value2;
        }

        public void setValue2(String value2) {
            this.value2 = value2;
        }

    }

}
