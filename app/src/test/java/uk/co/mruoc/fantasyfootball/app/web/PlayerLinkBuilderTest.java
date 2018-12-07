package uk.co.mruoc.fantasyfootball.app.web;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerLinkBuilderTest {

    private static final long PLAYER_ID = 3333;

    @Test
    public void shouldReturnPlayerLinkWithNoHostnameIfCurrentRequestNotSet() {
        final String link = PlayerLinkBuilder.build(PLAYER_ID);

        assertThat(link).isEqualTo("/players/" + PLAYER_ID);
    }

    @Test
    public void shouldReturnPlayerLinkEndpoint() {
        try {
            RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

            final String link = PlayerLinkBuilder.build(PLAYER_ID);

            assertThat(link).isEqualTo("http://localhost/players/" + PLAYER_ID);
        } finally {
            RequestContextHolder.resetRequestAttributes();
        }
    }

}
