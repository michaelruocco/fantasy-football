package uk.co.mruoc.fantasyfootball.app.service;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.ClubNotFoundErrorData;
import uk.co.mruoc.fantasyfootball.api.JsonApiException;

import static org.assertj.core.api.Assertions.assertThat;

public class ClubNotFoundExceptionTest {

    private static final long ID = 1234;
    private static final String EXPECTED_MESSAGE = String.format("club with id %d not found", ID);

    @Test
    public void shouldReturnMessage() {
        final Throwable throwable = new ClubNotFoundException(ID);

        assertThat(throwable.getMessage()).isEqualTo(EXPECTED_MESSAGE);
    }

    @Test
    public void shouldReturnErrorData() {
        final JsonApiException exception = new ClubNotFoundException(ID);

        assertThat(exception.getErrorData()).isInstanceOf(ClubNotFoundErrorData.class);
    }

}
