package uk.co.mruoc.fantasyfootball.dao;

import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ClubTest {

    private Club club = new Club();

    @Test
    public void shouldReturnId() {
        long id = 4444;

        club.setId(id);

        assertThat(club.getId()).isEqualTo(id);
    }

    @Test
    public void shouldReturnName() {
        String name = "Test Club";

        club.setName(name);

        assertThat(club.getName()).isEqualTo(name);
    }

}
