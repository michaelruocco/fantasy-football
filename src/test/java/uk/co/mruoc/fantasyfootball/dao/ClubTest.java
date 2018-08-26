package uk.co.mruoc.fantasyfootball.dao;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.ClubData;
import uk.co.mruoc.fantasyfootball.FakeClubData;
import uk.co.mruoc.fantasyfootball.FakeClubFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class ClubTest {

    private static final ClubData CLUB_DATA = new FakeClubData();

    @Test
    public void shouldReturnId() {
        Club club = FakeClubFactory.buildClub(CLUB_DATA);

        assertThat(club.getId()).isEqualTo(CLUB_DATA.getId());
    }

    @Test
    public void shouldReturnName() {
        Club club = FakeClubFactory.buildClub(CLUB_DATA);

        assertThat(club.getName()).isEqualTo(CLUB_DATA.getName());
    }

}
