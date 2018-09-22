package uk.co.mruoc.fantasyfootball.app.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerAlreadyExistsExceptionTest {

    @Test
    public void shouldReturnCorrectMessage() {
        final long id = 1234;
        final String expectedMessage = String.format("player with id %d already exists", id);

        final Throwable throwable = new PlayerAlreadyExistsException(id);

        assertThat(throwable.getMessage()).isEqualTo(expectedMessage);
    }

}
