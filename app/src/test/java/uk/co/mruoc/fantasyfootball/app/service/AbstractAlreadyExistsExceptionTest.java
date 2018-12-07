package uk.co.mruoc.fantasyfootball.app.service;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AbstractAlreadyExistsExceptionTest {

    private static final String MESSAGE = "test message";
    private static final long ID = 54321;

    @Test
    public void shouldReturnMessage() {
        AbstractAlreadyExistsException exception = new FakeAlreadyExistsException(MESSAGE, ID);

        assertThat(exception.getMessage()).isEqualTo(MESSAGE);
    }

    @Test
    public void shouldReturnId() {
        AbstractAlreadyExistsException exception = new FakeAlreadyExistsException(MESSAGE, ID);

        assertThat(exception.getId()).isEqualTo(ID);
    }

    private static class FakeAlreadyExistsException extends AbstractAlreadyExistsException {

        public FakeAlreadyExistsException(String message, long id) {
            super(message, id);
        }

    }

}
