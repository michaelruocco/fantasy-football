package uk.co.mruoc.fantasyfootball.app.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ClubNotFoundExceptionTest {

    @Test
    public void shouldReturnCorrectMessage() {
        final long id = 1234;
        final String expectedMessage = String.format("club with id %d not found", id);

        final Throwable throwable = new ClubNotFoundException(id);

        assertThat(throwable.getMessage()).isEqualTo(expectedMessage);
    }

}