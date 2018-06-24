package uk.co.mruoc.fantasyfootball;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.Team.TeamBuilder;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class TeamTest {

    private final TeamBuilder builder = new TeamBuilder();

    @Test
    public void shouldReturnRandomIdIfNotSet() {
        Team team = builder.build();

        assertThat(team.getId()).isNotNull();
    }

    @Test
    public void shouldReturnId() {
        UUID id = UUID.randomUUID();

        Team team = builder.setId(id).build();

        assertThat(team.getId()).isEqualTo(id);
    }

    @Test
    public void shouldReturnName() {
        String name = "custom team name";

        Team team = builder.setName(name).build();

        assertThat(team.getName()).isEqualTo(name);
    }

}
