package uk.co.mruoc.fantasyfootball.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ClubNotFoundExceptionTest {

    @Test
    public void shouldReturnCorrectMessage() {
        long id = 1234;
        String expectedMessage = String.format("club with id %d not found", id);

        Throwable throwable = new ClubNotFoundException(id);

        assertThat(throwable.getMessage()).isEqualTo(expectedMessage);
    }

}
