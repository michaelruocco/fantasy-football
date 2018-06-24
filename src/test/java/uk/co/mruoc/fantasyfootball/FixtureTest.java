package uk.co.mruoc.fantasyfootball;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static uk.co.mruoc.fantasyfootball.Fixture.*;

public class FixtureTest {

    private final FixtureBuilder builder = new FixtureBuilder();

    @Test
    public void shouldReturnRandomIdIfNotSet() {
        Fixture fixture = builder.build();

        assertThat(fixture.getId()).isNotNull();
    }

    @Test
    public void shouldReturnId() {
        UUID id = UUID.randomUUID();

        Fixture fixture = builder.setId(id).build();

        assertThat(fixture.getId()).isEqualTo(id);
    }

    @Test
    public void shouldReturnTeam1() {
        Team team = mock(Team.class);

        Fixture fixture = builder.setTeam1(team).build();

        assertThat(fixture.getTeam1()).isEqualTo(team);
    }

    @Test
    public void shouldReturnTeam2() {
        Team team = mock(Team.class);

        Fixture fixture = builder.setTeam2(team).build();

        assertThat(fixture.getTeam2()).isEqualTo(team);
    }

    @Test
    public void shouldReturnKickOff() {
        LocalDateTime kickOff = LocalDateTime.now();

        Fixture fixture = builder.setKickOff(kickOff).build();

        assertThat(fixture.getKickOff()).isEqualTo(kickOff);
    }

    @Test
    public void shouldReturnDate() {
        LocalDateTime kickOff = LocalDateTime.now();

        Fixture fixture = builder.setKickOff(kickOff).build();

        assertThat(fixture.getDate()).isEqualTo(kickOff.toLocalDate());
    }

}
