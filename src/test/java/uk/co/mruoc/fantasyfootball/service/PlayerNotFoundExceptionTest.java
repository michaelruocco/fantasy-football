package uk.co.mruoc.fantasyfootball.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerNotFoundExceptionTest {

    @Test
    public void shouldReturnCorrectMessage() {
        long id = 1234;
        String expectedMessage = String.format("player with id %d not found", id);

        Throwable throwable = new PlayerNotFoundException(id);

        assertThat(throwable.getMessage()).isEqualTo(expectedMessage);
    }

}
