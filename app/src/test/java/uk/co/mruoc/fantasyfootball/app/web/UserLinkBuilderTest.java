package uk.co.mruoc.fantasyfootball.app.web;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;

public class UserLinkBuilderTest {

    private static final long ID = 3333;

    @Test
    public void shouldReturnUserLinkWithNoHostnameIfCurrentRequestNotSet() {
        final String link = UserLinkBuilder.build(ID);

        assertThat(link).isEqualTo("/users/" + ID);
    }

    @Test
    public void shouldReturnUserLinkEndpoint() {
        try {
            RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

            final String link = UserLinkBuilder.build(ID);

            assertThat(link).isEqualTo("http://localhost/users/" + ID);
        } finally {
            RequestContextHolder.resetRequestAttributes();
        }
    }

}
