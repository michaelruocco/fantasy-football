package uk.co.mruoc.fantasyfootball.api;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class PlayerNotFoundErrorDataTest {

    private static final long PLAYER_ID = 999;
    private static final String DETAIL = "detail message";

    private final ErrorData error = new PlayerNotFoundErrorData(PLAYER_ID, DETAIL);

    @Test
    public void shouldReturnData() {
        assertThat(error.getId()).isNotNull();
        assertThat(error.getCode()).isEqualTo("PLAYER_NOT_FOUND");
        assertThat(error.getTitle()).isEqualTo("Player not found");
        assertThat(error.getDetail()).isEqualTo(DETAIL);
        assertThat(error.getMeta()).containsOnly(entry("id", PLAYER_ID));
    }

}
