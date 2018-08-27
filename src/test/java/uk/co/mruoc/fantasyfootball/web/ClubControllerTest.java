package uk.co.mruoc.fantasyfootball.web;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.ClubPlayersDocument;
import uk.co.mruoc.fantasyfootball.dao.Club;
import uk.co.mruoc.fantasyfootball.dao.Player;
import uk.co.mruoc.fantasyfootball.service.ClubService;

import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ClubControllerTest {

    private final ClubService service = mock(ClubService.class);
    private final ClubConverter clubConverter = mock(ClubConverter.class);
    private final PlayerConverter playerConverter = mock(PlayerConverter.class);

    private final ClubDocument document = new ClubDocument();
    private final Club club = new Club();

    private final ClubController controller = new ClubController(service, clubConverter, playerConverter);

    @Test
    public void shouldCreateClub() {
        final Club createdClub = new Club();
        final ClubDocument expectedDocument = new ClubDocument();

        given(clubConverter.toClub(document)).willReturn(club);
        given(service.upsert(club)).willReturn(createdClub);
        given(clubConverter.toDocument(createdClub)).willReturn(expectedDocument);

        final ClubDocument resultDocument = controller.create(document);

        assertThat(resultDocument).isEqualTo(expectedDocument);
    }

    @Test
    public void shouldReadClub() {
        final long id = 1212;
        final Club club = new Club();
        final ClubDocument expectedDocument = new ClubDocument();

        given(service.read(id)).willReturn(club);
        given(clubConverter.toDocument(club)).willReturn(expectedDocument);

        final ClubDocument resultDocument = controller.read(id);

        assertThat(resultDocument).isEqualTo(expectedDocument);
    }

    @Test
    public void shouldReadClubPlayers() {
        final long clubId = 1212;
        final int pageNumber = 0;
        final int pageSize = 2;
        final Page<Player> page = new PageImpl<>(singletonList(new Player()));
        final ClubPlayersDocument expectedDocument = new ClubPlayersDocument();

        given(service.readPlayersByClubId(clubId, pageNumber, pageSize)).willReturn(page);
        given(playerConverter.toDocument(clubId, page)).willReturn(expectedDocument);

        final ClubPlayersDocument resultDocument = controller.readPlayers(clubId, pageNumber, pageSize);

        assertThat(resultDocument).isEqualTo(expectedDocument);
    }

}
