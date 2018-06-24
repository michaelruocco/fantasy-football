package uk.co.mruoc.fantasyfootball;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TeamNotFoundExceptionTest {

    @Test
    public void shouldReturnMessage() {
        String name = "team name";

        Exception exception = new TeamNotFoundException(name);

        assertThat(exception.getMessage()).isEqualTo("team not found with name " + name);
    }

}
