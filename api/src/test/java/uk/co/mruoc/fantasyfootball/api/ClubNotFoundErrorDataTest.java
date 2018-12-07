package uk.co.mruoc.fantasyfootball.api;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class ClubNotFoundErrorDataTest {

    private static final long CLUB_ID = 999;
    private static final String DETAIL = "detail message";

    private final ErrorData error = new ClubNotFoundErrorData(CLUB_ID, DETAIL);

    @Test
    public void shouldReturnData() {
        assertThat(error.getId()).isNotNull();
        assertThat(error.getCode()).isEqualTo("CLUB_NOT_FOUND");
        assertThat(error.getTitle()).isEqualTo("Club not found");
        assertThat(error.getDetail()).isEqualTo(DETAIL);
        assertThat(error.getMeta()).containsOnly(entry("id", CLUB_ID));
    }

}
