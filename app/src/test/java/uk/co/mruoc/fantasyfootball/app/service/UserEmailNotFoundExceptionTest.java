package uk.co.mruoc.fantasyfootball.app.service;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.JsonApiException;
import uk.co.mruoc.fantasyfootball.api.UserEmailNotFoundErrorData;

import static org.assertj.core.api.Assertions.assertThat;

public class UserEmailNotFoundExceptionTest {

    private static final String EMAIL = "michael.ruocco@hotmail.com";
    private static final String EXPECTED_MESSAGE = String.format("user with email %s not found", EMAIL);

    @Test
    public void shouldReturnMessage() {
        final Throwable throwable = new UserEmailNotFoundException(EMAIL);

        assertThat(throwable.getMessage()).isEqualTo(EXPECTED_MESSAGE);
    }

    @Test
    public void shouldReturnErrorData() {
        final JsonApiException exception = new UserEmailNotFoundException(EMAIL);

        assertThat(exception.getErrorData()).isInstanceOf(UserEmailNotFoundErrorData.class);
    }

}
