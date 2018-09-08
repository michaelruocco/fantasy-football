package uk.co.mruoc.fantasyfootball.app.web;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;

public class ClubLinkBuilderTest {

    private static final long CLUB_ID = 2222;

    @Test
    public void shouldReturnEmptyStringIfCurrentRequestCannotBeFound() {
        final String link = ClubLinkBuilder.build(CLUB_ID);

        assertThat(link).isEqualTo("/clubs/2222");
    }

    @Test
    public void shouldReturnLinkIfCurrentRequestIsSetup() {
        try {
            RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

            final String link = ClubLinkBuilder.build(CLUB_ID);

            assertThat(link).isEqualTo("http://localhost/clubs/2222");
        } finally {
            RequestContextHolder.resetRequestAttributes();
        }
    }

}