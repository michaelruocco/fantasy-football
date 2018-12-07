package uk.co.mruoc.fantasyfootball.api;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.fantasyfootball.api.ErrorData.ErrorDataBuilder;

public class ErrorDataTest {

    private final ErrorDataBuilder builder = new ErrorDataBuilder();

    @Test
    public void shouldSetId() {
        final UUID id = UUID.randomUUID();

        final ErrorData data = builder.setId(id).build();

        assertThat(data.getId()).isEqualTo(id);
    }

    @Test
    public void shouldSetCode() {
        final String code = "my code";

        final ErrorData data = builder.setCode(code).build();

        assertThat(data.getCode()).isEqualTo(code);
    }

    @Test
    public void shouldSetTitle() {
        final String title = "my title";

        final ErrorData data = builder.setTitle(title).build();

        assertThat(data.getTitle()).isEqualTo(title);
    }

    @Test
    public void shouldSetDetail() {
        final String detail = "my detail";

        final ErrorData data = builder.setDetail(detail).build();

        assertThat(data.getDetail()).isEqualTo(detail);
    }

    @Test
    public void shouldSetMeta() {
        final Map<String, Object> meta = new HashMap<>();

        final ErrorData data = builder.setMeta(meta).build();

        assertThat(data.getMeta()).isEqualTo(meta);
    }

}
