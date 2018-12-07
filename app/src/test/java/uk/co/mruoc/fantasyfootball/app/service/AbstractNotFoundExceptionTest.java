package uk.co.mruoc.fantasyfootball.app.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractNotFoundExceptionTest {

    private static final String MESSAGE = "test message";
    private static final long ID = 54321;

    @Test
    public void shouldReturnMessage() {
        AbstractNotFoundException exception = new FakeNotFoundException(MESSAGE, ID);

        assertThat(exception.getMessage()).isEqualTo(MESSAGE);
    }

    @Test
    public void shouldReturnId() {
        AbstractNotFoundException exception = new FakeNotFoundException(MESSAGE, ID);

        assertThat(exception.getId()).isEqualTo(ID);
    }

    private static class FakeNotFoundException extends AbstractNotFoundException {

        public FakeNotFoundException(String message, long id) {
            super(message, id);
        }

    }

}
