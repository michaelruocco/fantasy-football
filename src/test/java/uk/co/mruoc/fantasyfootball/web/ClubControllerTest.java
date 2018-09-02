package uk.co.mruoc.fantasyfootball.web;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.co.mruoc.fantasyfootball.FakeClubFactory;
import uk.co.mruoc.fantasyfootball.FakePlayerFactory;
import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.ClubPlayersDocument;
import uk.co.mruoc.fantasyfootball.dao.Club;
import uk.co.mruoc.fantasyfootball.dao.Player;
import uk.co.mruoc.fantasyfootball.service.ClubService;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ClubControllerTest {

    private final ClubService service = mock(ClubService.class);

    private final ClubDocument document = FakeClubFactory.buildClubDocument1();
    private final Club club = FakeClubFactory.buildClub1();

    private final ClubController controller = new ClubController(service);

    @Test
    public void shouldConvertDocumentToClubOnCreate() {
        final ArgumentCaptor<Club> clubCaptor = ArgumentCaptor.forClass(Club.class);
        given(service.upsert(any(Club.class))).willReturn(club);

        controller.create(document);

        verify(service).upsert(clubCaptor.capture());
        assertThat(clubCaptor.getValue()).isEqualToComparingFieldByFieldRecursively(club);
    }

    @Test
    public void shouldConverterCreatedClubIntoDocument() {
        given(service.upsert(any(Club.class))).willReturn(club);

        final ResponseEntity<ClubDocument> entity = controller.create(document);

        assertThat(entity.getBody()).isEqualToComparingFieldByFieldRecursively(document);
    }

    @Test
    public void shouldReturnCreatedStatusCode() {
        given(service.upsert(any(Club.class))).willReturn(club);

        final ResponseEntity<ClubDocument> entity = controller.create(document);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void shouldReturnLocationHeaderWithNewResourceUrl() {
        given(service.upsert(any(Club.class))).willReturn(club);

        final ResponseEntity<ClubDocument> entity = controller.create(document);

        final HttpHeaders headers = entity.getHeaders();

        assertThat(headers.get("Location")).containsExactly("/clubs/1234");
    }

    @Test
    public void shouldReadClub() {
        given(service.read(document.getId())).willReturn(club);

        final ClubDocument resultDocument = controller.read(document.getId());

        assertThat(resultDocument).isEqualToComparingFieldByFieldRecursively(document);
    }

    @Test
    public void shouldReadClubPlayers() {
        final ClubPlayersDocument expectedDocument = FakePlayerFactory.buildClubPlayersDocumentWithNoData();
        final Page<Player> page = toPage(expectedDocument);
        final long clubId = expectedDocument.getClubId();
        final int pageNumber = expectedDocument.getPageNumber();
        final int pageSize = expectedDocument.getPageSize();
        given(service.readPlayersByClubId(clubId, pageNumber, pageSize)).willReturn(page);

        final ClubPlayersDocument resultDocument = controller.readPlayers(clubId, pageNumber, pageSize);

        assertThat(resultDocument).isEqualToComparingFieldByFieldRecursively(expectedDocument);
    }

    private static Page<Player> toPage(final ClubPlayersDocument document) {
        return buildEmptyPage(document.getPageNumber(), document.getPageSize(), document.getTotalPages());
    }

    private static Page<Player> buildEmptyPage(int pageNumber, int pageSize, long totalPages) {
        return new PageImpl<>(emptyList(), PageRequest.of(pageNumber, pageSize), totalPages);
    }

}
