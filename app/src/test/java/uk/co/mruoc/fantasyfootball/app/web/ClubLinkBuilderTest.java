package uk.co.mruoc.fantasyfootball.app.web;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;

public class ClubLinkBuilderTest {

    private static final long CLUB_ID = 2222;
    private static final int PAGE_NUMBER = 2;
    private static final int PAGE_SIZE = 5;

    @Test
    public void shouldReturnClubEndpointWithNoHostnameIfCurrentRequestNotSet() {
        final String link = ClubLinkBuilder.build(CLUB_ID);

        assertThat(link).isEqualTo("/clubs/2222");
    }

    @Test
    public void shouldReturnClubEndpointLink() {
        try {
            RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

            final String link = ClubLinkBuilder.build(CLUB_ID);

            assertThat(link).isEqualTo("http://localhost/clubs/2222");
        } finally {
            RequestContextHolder.resetRequestAttributes();
        }
    }

    @Test
    public void shouldReturnClubsPaginationEndpointWithNoHostnameIfCurrentRequestNotSet() {
        final String link = ClubLinkBuilder.build(PAGE_NUMBER, PAGE_SIZE);

        assertThat(link).isEqualTo("/clubs?pageNumber=2&pageSize=5");
    }

    @Test
    public void shouldReturnClubsPaginationEndpointLink() {
        try {
            RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

            final String link = ClubLinkBuilder.build(PAGE_NUMBER, PAGE_SIZE);

            assertThat(link).isEqualTo("http://localhost/clubs?pageNumber=2&pageSize=5");
        } finally {
            RequestContextHolder.resetRequestAttributes();
        }
    }

}