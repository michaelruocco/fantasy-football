package uk.co.mruoc.fantasyfootball.app.service;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.JsonApiException;
import uk.co.mruoc.fantasyfootball.api.UserIdNotFoundErrorData;

import static org.assertj.core.api.Assertions.assertThat;

public class UserIdNotFoundExceptionTest {

    private static final long ID  = 1234;
    private static final String EXPECTED_MESSAGE = String.format("user with id %d not found", ID);

    @Test
    public void shouldReturnMessage() {
        final Throwable throwable = new UserIdNotFoundException(ID);

        assertThat(throwable.getMessage()).isEqualTo(EXPECTED_MESSAGE);
    }

    @Test
    public void shouldReturnErrorData() {
        final JsonApiException exception = new UserIdNotFoundException(ID);

        assertThat(exception.getErrorData()).isInstanceOf(UserIdNotFoundErrorData.class);
    }

}
