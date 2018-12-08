package uk.co.mruoc.fantasyfootball.app.web;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;

public class ClubPlayersLinkBuilderTest {

    private static final long CLUB_ID = 2222;
    private static final int PAGE_NUMBER = 0;
    private static final int PAGE_SIZE = 10;

    private final LinkBuilder linkBuilder = new ClubPlayersLinkBuilder(CLUB_ID);

    @Test
    public void shouldReturnClubPlayersPaginatedEndpointWithNoHostnameIfCurrentRequestNotSet() {
        final String link = linkBuilder.build(PAGE_NUMBER, PAGE_SIZE);

        assertThat(link).isEqualTo("/clubs/2222/players?pageNumber=0&pageSize=10");
    }

    @Test
    public void shouldReturnClubPlayersPaginatedEndpoint() {
        try {
            RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

            final String link = linkBuilder.build(PAGE_NUMBER, PAGE_SIZE);

            assertThat(link).isEqualTo("http://localhost/clubs/2222/players?pageNumber=0&pageSize=10");
        } finally {
            RequestContextHolder.resetRequestAttributes();
        }
    }

}
