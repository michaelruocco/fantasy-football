package uk.co.mruoc.fantasyfootball;

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

}
