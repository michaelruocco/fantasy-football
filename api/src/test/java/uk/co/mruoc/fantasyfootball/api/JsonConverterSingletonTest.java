package uk.co.mruoc.fantasyfootball.api;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonConverterSingletonTest {

    @Test
    public void shouldReturnSingletonInstanceOfJsonConverter() {
        JsonConverter converter1 = JsonConverterSingleton.get();
        JsonConverter converter2 = JsonConverterSingleton.get();

        assertThat(converter1).isEqualTo(converter2);
    }

}
