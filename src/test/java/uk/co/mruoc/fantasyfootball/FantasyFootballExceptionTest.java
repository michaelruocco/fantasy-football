package uk.co.mruoc.fantasyfootball;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FantasyFootballExceptionTest {

    @Test
    public void shouldReturnCause() {
        Throwable cause = new Exception();

        Exception exception = new FantasyFootballException(cause);

        assertThat(exception.getCause()).isEqualTo(cause);
    }

    @Test
    public void shouldReturnMessage() {
        String message = "custom error message";

        Exception exception = new FantasyFootballException(message);

        assertThat(exception.getMessage()).isEqualTo(message);
    }

}
