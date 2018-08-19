package uk.co.mruoc.fantasyfootball.dao;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerTest {

    private Player player = new Player();

    @Test
    public void shouldReturnId() {
        long id = 1111;

        player.setId(id);

        assertThat(player.getId()).isEqualTo(id);
    }

    @Test
    public void shouldReturnTrueIfPlayerHasId() {
        long id = 1111;

        player.setId(id);

        assertThat(player.hasId()).isTrue();
    }

    @Test
    public void shouldReturnFalseIfPlayerDoesNotHaveId() {
        assertThat(player.hasId()).isFalse();
    }

    @Test
    public void shouldReturnFirstName() {
        String firstName = "Joe";

        player.setFirstName(firstName);

        assertThat(player.getFirstName()).isEqualTo(firstName);
    }

    @Test
    public void shouldReturnLastName() {
        String lastName = "Bloggs";

        player.setLastName(lastName);

        assertThat(player.getLastName()).isEqualTo(lastName);
    }

    @Test
    public void shouldReturnPosition() {
        String position = "DEFENDER";

        player.setPosition(position);

        assertThat(player.getPosition()).isEqualTo(position);
    }

    @Test
    public void shouldReturnValue() {
        int value = 500000;

        player.setValue(value);

        assertThat(player.getValue()).isEqualTo(value);
    }

    @Test
    public void shouldReturnClub() {
        Club club = new Club();

        player.setClub(club);

        assertThat(player.getClub()).isEqualTo(club);
    }

    @Test
    public void shouldReturnTrueIfPlayerHasClub() {
        Club club = new Club();

        player.setClub(club);

        assertThat(player.hasClub()).isTrue();
    }

    @Test
    public void shouldReturnClubIdIfPlayerHasClub() {
        Club club = new Club(9797);

        player.setClub(club);

        assertThat(player.getClubId()).isEqualTo(Optional.of(club.getId()));
    }

    @Test
    public void shouldReturnFalseIfPlayerDoesNotHaveClub() {
        assertThat(player.hasClub()).isFalse();
    }

    @Test
    public void shouldReturnEmptyOptionalClubIdIfPlayerDoesHasClub() {
        assertThat(player.getClubId()).isEqualTo(Optional.empty());
    }

}
