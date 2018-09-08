package uk.co.mruoc.fantasyfootball.app.web;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InvalidLinkExceptionTest {

    @Test
    public void shouldReturnCause() {
        final Throwable cause = new Exception("my cause");

        final Throwable exception = new InvalidLinkException(cause);

        assertThat(exception.getCause()).isEqualTo(cause);
    }

}
