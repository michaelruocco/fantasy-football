package uk.co.mruoc.fantasyfootball.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class JacksonConfigTest {

    private final JacksonConfig configuration = new JacksonConfig();

    @Test
    public void shouldIgnoreNullsWhenSerializingObjects() throws IOException {
        final ObjectMapper mapper = configuration.objectMapper();
        final FakeBean bean = new FakeBean();
        bean.setValue1("test value 1");

        final String json = mapper.writeValueAsString(bean);

        assertThat(json).isEqualTo("{\"value1\":\"test value 1\"}");
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
