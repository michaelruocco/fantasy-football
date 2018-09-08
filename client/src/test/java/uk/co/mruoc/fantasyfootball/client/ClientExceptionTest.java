package uk.co.mruoc.fantasyfootball.client;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientExceptionTest {

    @Test
    public void shouldReturnCause() {
        final Throwable cause = new Exception();

        final Throwable exception = new ClientException(cause);

        assertThat(exception.getCause()).isEqualTo(cause);
    }

}
