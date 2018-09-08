package uk.co.mruoc.fantasyfootball.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class JsonConverterTest {

    private static final String BEAN_JSON = "{\"value1\":\"my first value\",\"value2\":null}";
    private final ObjectMapper mapper = mock(ObjectMapper.class);

    private final JsonConverter converter = new JsonConverter(mapper);

    @Test
    public void shouldConvertObjectToJson() throws JsonProcessingException {
        final FakeBean bean = buildFakeBean();
        given(mapper.writeValueAsString(bean)).willReturn(BEAN_JSON);

        final String json = converter.toJson(bean);

        assertThat(json).isEqualTo(BEAN_JSON);
    }

    @Test
    public void shouldThrowClientExceptionIfSerializationErrors() throws JsonProcessingException {
        final FakeBean bean = buildFakeBean();
        given(mapper.writeValueAsString(bean)).willThrow(JsonProcessingException.class);

        final Throwable thrown = catchThrowable(() -> converter.toJson(bean));

        assertThat(thrown)
                .isInstanceOf(ClientException.class)
                .hasCauseInstanceOf(JsonProcessingException.class);
    }

    @Test
    public void shouldConvertObjectFromJson() throws IOException {
        final FakeBean expectedBean = buildFakeBean();
        given(mapper.readValue(BEAN_JSON, FakeBean.class)).willReturn(expectedBean);

        final FakeBean bean = converter.fromJson(BEAN_JSON, FakeBean.class);

        assertThat(bean).isEqualToComparingFieldByField(expectedBean);
    }

    @Test
    public void shouldThrowClientExceptionIfDeserializationFails() throws IOException {
        given(mapper.readValue(BEAN_JSON, FakeBean.class)).willThrow(IOException.class);

        final Throwable thrown = catchThrowable(() -> converter.fromJson(BEAN_JSON, FakeBean.class));

        assertThat(thrown)
                .isInstanceOf(ClientException.class)
                .hasCauseInstanceOf(IOException.class);
    }

    private FakeBean buildFakeBean() {
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
