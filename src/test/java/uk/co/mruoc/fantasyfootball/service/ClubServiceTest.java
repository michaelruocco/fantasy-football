package uk.co.mruoc.fantasyfootball.service;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import uk.co.mruoc.fantasyfootball.dao.Club;
import uk.co.mruoc.fantasyfootball.dao.ClubRepository;
import uk.co.mruoc.fantasyfootball.dao.Player;
import uk.co.mruoc.fantasyfootball.dao.PlayerRepository;

import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ClubServiceTest {

    private static final long CLUB_ID = 1234;

    private final ClubRepository clubRepository = mock(ClubRepository.class);
    private final PlayerRepository playerRepository = mock(PlayerRepository.class);

    private final ClubService service = new ClubService(clubRepository, playerRepository);

    @Test
    public void shouldReturnClubIfFound() {
        Club expectedClub = mock(Club.class);
        given(clubRepository.findById(CLUB_ID)).willReturn(Optional.of(expectedClub));

        Club club = service.read(CLUB_ID);

        assertThat(club).isEqualTo(expectedClub);
    }

    @Test
    public void shouldThrowExceptionIfClubNotFound() {
        given(clubRepository.findById(CLUB_ID)).willReturn(Optional.empty());
        String expectedMessage = String.format("club with id %d not found", CLUB_ID);

        Throwable thrown = catchThrowable(() -> { service.read(CLUB_ID); });

        assertThat(thrown).isInstanceOf(ClubNotFoundException.class)
                .hasNoCause()
                .hasMessage(expectedMessage);
    }

    @Test
    public void shouldUpsertClub() {
        Club club = mock(Club.class);
        Club expectedClub = mock(Club.class);
        given(clubRepository.save(club)).willReturn(expectedClub);

        Club resultClub = service.upsert(club);

        assertThat(resultClub).isEqualTo(expectedClub);
    }

    @Test
    public void shouldReadPageOfClubPlayers() {
        Page<Player> expectedPage = new PageImpl<>(emptyList());
        given(playerRepository.findByClub(any(Club.class), any(Pageable.class))).willReturn(expectedPage);

        Page<Player> page = service.readPlayersByClubId(CLUB_ID, 1, 2);

        assertThat(page).isEqualTo(expectedPage);
    }

    @Test
    public void shouldPassCorrectArgumentsWhenReadingPageOfClubPlayers() {
        int pageNumber = 1;
        int pageSize = 2;
        ArgumentCaptor<Club> club = ArgumentCaptor.forClass(Club.class);
        ArgumentCaptor<Pageable> page = ArgumentCaptor.forClass(Pageable.class);

        service.readPlayersByClubId(CLUB_ID, pageNumber, pageSize);

        verify(playerRepository).findByClub(club.capture(), page.capture());
        assertThat(club.getValue().getId()).isEqualTo(CLUB_ID);
        assertThat(page.getValue().getPageNumber()).isEqualTo(pageNumber);
        assertThat(page.getValue().getPageSize()).isEqualTo(pageSize);
    }

}
