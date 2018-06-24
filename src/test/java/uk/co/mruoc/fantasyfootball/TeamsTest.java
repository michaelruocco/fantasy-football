package uk.co.mruoc.fantasyfootball;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.Team.TeamBuilder;

import java.util.Arrays;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class TeamsTest {

    private final Team bTeam = new TeamBuilder().setName("b-team").build();
    private final Team aTeam = new TeamBuilder().setName("a-team").build();

    private final Teams teams = new Teams(Arrays.asList(bTeam, aTeam));

    @Test
    public void shouldBeIterableAndInAlphabeticalOrder() {
        Iterator<Team> iterator = teams.iterator();

        assertThat(iterator.next()).isEqualTo(aTeam);
        assertThat(iterator.next()).isEqualTo(bTeam);
        assertThat(iterator.hasNext()).isFalse();
    }

    @Test
    public void shouldReturnTeamByName() {
        assertThat(teams.get(aTeam.getName())).isEqualTo(aTeam);
        assertThat(teams.get(bTeam.getName())).isEqualTo(bTeam);
    }

    @Test
    public void shouldThrowTeamNotFoundExceptionIfTeamDoesNotExist() {
        Throwable thrown = catchThrowable(() -> teams.get("c-team"));

        assertThat(thrown)
                .isInstanceOf(TeamNotFoundException.class)
                .hasMessage("team not found with name c-team");
    }

}
