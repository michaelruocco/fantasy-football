package uk.co.mruoc.fantasyfootball.app.service;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.JsonApiException;
import uk.co.mruoc.fantasyfootball.api.PlayerNotFoundErrorData;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerNotFoundExceptionTest {

    private static final long ID = 1234;
    private static final String EXPECTED_MESSAGE = String.format("player with id %d not found", ID);

    @Test
    public void shouldReturnMessage() {
        final Throwable throwable = new PlayerNotFoundException(ID);

        assertThat(throwable.getMessage()).isEqualTo(EXPECTED_MESSAGE);
    }

    @Test
    public void shouldReturnErrorData() {
        final JsonApiException exception = new PlayerNotFoundException(ID);

        assertThat(exception.getErrorData()).isInstanceOf(PlayerNotFoundErrorData.class);
    }

}
