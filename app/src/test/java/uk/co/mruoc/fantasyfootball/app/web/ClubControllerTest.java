package uk.co.mruoc.fantasyfootball.app.web;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.co.mruoc.fantasyfootball.api.ArrayDocument;
import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.ClubDocument.ClubData;
import uk.co.mruoc.fantasyfootball.api.ClubsDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument.PlayerData;
import uk.co.mruoc.fantasyfootball.api.PlayersDocument.PlayersDocumentBuilder;
import uk.co.mruoc.fantasyfootball.app.dao.Club;
import uk.co.mruoc.fantasyfootball.app.dao.Player;
import uk.co.mruoc.fantasyfootball.app.service.ClubService;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class ClubControllerTest {

    private static final int DEFAULT_PAGE_SIZE = 10;
    private static final int PAGE_NUMBER = 1;

    private static final long ID = 54321L;
    private static final String NAME = "Test Club";
    private static final String SELF_LINK = "/club/" + ID;

    private final ClubService service = mock(ClubService.class);
    private final ClubConverter clubConverter = mock(ClubConverter.class);
    private final PlayerConverter playerConverter = mock(PlayerConverter.class);

    private final ClubDocument document = buildClubDocument();
    private final Club club = new Club();

    private final ClubController controller = new ClubController(service, clubConverter, playerConverter);

    @Test
    public void shouldCreateClub() {
        given(clubConverter.toClub(document)).willReturn(club);
        final Club createdClub = new Club();
        given(service.create(club)).willReturn(createdClub);
        final ClubDocument createdDocument = buildClubDocument();
        given(clubConverter.toDocument(createdClub, DEFAULT_PAGE_SIZE)).willReturn(createdDocument);

        final ResponseEntity<ClubDocument> entity = controller.create(document);

        assertThat(entity.getBody()).isEqualTo(createdDocument);
    }

    @Test
    public void shouldReturnCreatedStatusCodeOnCreate() {
        given(clubConverter.toClub(document)).willReturn(club);
        given(service.create(club)).willReturn(club);
        given(clubConverter.toDocument(club, DEFAULT_PAGE_SIZE)).willReturn(document);

        final ResponseEntity<ClubDocument> entity = controller.create(document);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void shouldReturnOkStatusCodeOnCreateWithIdThatAlreadyExists() {
        given(clubConverter.toClub(document)).willReturn(club);
        given(service.exists(document.getId())).willReturn(true);
        given(service.create(club)).willReturn(club);
        given(clubConverter.toDocument(club, DEFAULT_PAGE_SIZE)).willReturn(document);

        final ResponseEntity<ClubDocument> entity = controller.create(document);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReturnLocationHeaderWithNewResourceUrlOnCreate() {
        given(clubConverter.toClub(document)).willReturn(club);
        given(service.create(club)).willReturn(club);
        given(clubConverter.toDocument(club, DEFAULT_PAGE_SIZE)).willReturn(document);

        final ResponseEntity<ClubDocument> entity = controller.create(document);

        final HttpHeaders headers = entity.getHeaders();
        assertThat(headers.get("Location")).containsExactly(SELF_LINK);
    }

    @Test
    public void shouldConvertDocumentToClubOnUpdate() {
        given(clubConverter.toClub(document.getId(), document)).willReturn(club);
        final Club updatedClub = new Club();
        given(service.update(club)).willReturn(updatedClub);
        final ClubDocument updatedDocument = buildClubDocument();
        given(clubConverter.toDocument(updatedClub, DEFAULT_PAGE_SIZE)).willReturn(updatedDocument);

        final ResponseEntity<ClubDocument> entity = controller.update(document.getId(), document);

        assertThat(entity.getBody()).isEqualTo(updatedDocument);
    }

    @Test
    public void shouldReturnOkStatusCodeOnUpdate() {
        given(clubConverter.toClub(document.getId(), document)).willReturn(club);
        given(service.update(club)).willReturn(club);
        given(clubConverter.toDocument(club, DEFAULT_PAGE_SIZE)).willReturn(document);

        final ResponseEntity<ClubDocument> entity = controller.update(document.getId(), document);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReadClub() {
        given(service.read(document.getId())).willReturn(club);
        final ClubDocument readDocument = new ClubDocument();
        given(clubConverter.toDocument(club, DEFAULT_PAGE_SIZE)).willReturn(readDocument);

        final ClubDocument resultDocument = controller.read(document.getId());

        assertThat(resultDocument).isEqualTo(readDocument);
    }

    @Test
    public void shouldReadClubPlayers() {
        final ArrayDocument<PlayerData> expectedDocument = new PlayersDocumentBuilder()
                .setPageNumber(PAGE_NUMBER)
                .setPageSize(DEFAULT_PAGE_SIZE)
                .build();
        final Page<Player> page = toPlayerPage(expectedDocument);
        given(service.readPlayersByClubId(ID, PAGE_NUMBER, DEFAULT_PAGE_SIZE)).willReturn(page);
        given(playerConverter.toClubPlayersDocument(ID, page)).willReturn(expectedDocument);

        final ArrayDocument<PlayerData> resultDocument = controller.readPlayers(ID, PAGE_NUMBER, DEFAULT_PAGE_SIZE);

        assertThat(resultDocument).isEqualTo(expectedDocument);
    }

    @Test
    public void shouldReadClubs() {
        final ArrayDocument<ClubData> expectedDocument = new ClubsDocument.ClubsDocumentBuilder()
                .setPageNumber(PAGE_NUMBER)
                .setPageSize(DEFAULT_PAGE_SIZE)
                .build();
        final Page<Club> page = toClubPage(expectedDocument);
        given(service.read(PAGE_NUMBER, DEFAULT_PAGE_SIZE)).willReturn(page);
        given(clubConverter.toDocument(page)).willReturn(expectedDocument);

        final ArrayDocument<ClubData> resultDocument = controller.read(PAGE_NUMBER, DEFAULT_PAGE_SIZE);

        assertThat(resultDocument).isEqualTo(expectedDocument);
    }

    private static ClubDocument buildClubDocument() {
        return new ClubDocument.ClubDocumentBuilder()
                .setId(ID)
                .setName(NAME)
                .setSelfLink(SELF_LINK)
                .build();
    }


    private static Page<Player> toPlayerPage(final ArrayDocument<PlayerData> document) {
        return buildEmptyPlayerPage(document.getPageNumber(), document.getPageSize(), document.getTotalPages());
    }

    private static Page<Club> toClubPage(final ArrayDocument<ClubData> document) {
        return buildEmptyClubPage(document.getPageNumber(), document.getPageSize(), document.getTotalPages());
    }

    private static Page<Player> buildEmptyPlayerPage(int pageNumber, int pageSize, long totalPages) {
        return new PageImpl<>(emptyList(), PageRequest.of(pageNumber, pageSize), totalPages);
    }

    private static Page<Club> buildEmptyClubPage(int pageNumber, int pageSize, long totalPages) {
        return new PageImpl<>(emptyList(), PageRequest.of(pageNumber, pageSize), totalPages);
    }

}
