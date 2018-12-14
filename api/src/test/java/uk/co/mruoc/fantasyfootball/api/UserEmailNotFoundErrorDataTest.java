package uk.co.mruoc.fantasyfootball.api;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class UserEmailNotFoundErrorDataTest {

    private static final String EMAIL = "michael.ruocco@hotmail.com";
    private static final String DETAIL = "detail message";

    private final ErrorData error = new UserEmailNotFoundErrorData(EMAIL, DETAIL);

    @Test
    public void shouldReturnData() {
        assertThat(error.getId()).isNotNull();
        assertThat(error.getCode()).isEqualTo("USER_NOT_FOUND");
        assertThat(error.getTitle()).isEqualTo("User not found");
        assertThat(error.getDetail()).isEqualTo(DETAIL);
        assertThat(error.getMeta()).containsOnly(entry("email", EMAIL));
    }

}
