package uk.co.mruoc.fantasyfootball.app.dao;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ClubTest {

    private static final long ID = 12345L;
    private static final String NAME = "Test Club";

    private final Club club = new Club(ID, NAME);

    @Test
    public void shouldReturnId() {
        assertThat(club.getId()).isEqualTo(ID);
    }

    @Test
    public void shouldReturnTrueIfHasId() {
        assertThat(club.hasId()).isTrue();
    }

    @Test
    public void shouldReturnFalseIfDoesNotHaveId() {
        Club clubWithNoId = new Club();

        assertThat(clubWithNoId.hasId()).isFalse();
    }

    @Test
    public void shouldReturnName() {
        assertThat(club.getName()).isEqualTo(NAME);
    }

}
