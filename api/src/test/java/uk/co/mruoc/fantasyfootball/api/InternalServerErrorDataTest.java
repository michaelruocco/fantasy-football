package uk.co.mruoc.fantasyfootball.api;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class InternalServerErrorDataTest {

    private static final String DETAIL = "detail message";

    private final ErrorData error = new InternalServerErrorData(DETAIL);

    @Test
    public void shouldReturnData() {
        assertThat(error.getId()).isNotNull();
        assertThat(error.getCode()).isEqualTo("INTERNAL_SERVER_ERROR");
        assertThat(error.getTitle()).isEqualTo("Internal server error");
        assertThat(error.getDetail()).isEqualTo(DETAIL);
        assertThat(error.getMeta()).isNull();
    }

}
