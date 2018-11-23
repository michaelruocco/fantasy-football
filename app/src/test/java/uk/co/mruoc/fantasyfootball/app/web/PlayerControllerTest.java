package uk.co.mruoc.fantasyfootball.app.web;

import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.co.mruoc.fantasyfootball.api.ArrayDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument.PlayerData;
import uk.co.mruoc.fantasyfootball.api.PlayersDocument;
import uk.co.mruoc.fantasyfootball.app.dao.Club;
import uk.co.mruoc.fantasyfootball.app.dao.Player;
import uk.co.mruoc.fantasyfootball.app.dao.Position;
import uk.co.mruoc.fantasyfootball.app.service.PlayerService;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@Ignore
public class PlayerControllerTest {

    private final PlayerService service = mock(PlayerService.class);

    private final PlayerDocument document = new PlayerDocument();
    private final Player player = toPlayer(document.getData());

    private final PlayerController controller = new PlayerController(service);

    @Test
    public void shouldConvertDocumentToPlayerOnCreate() {
        final ArgumentCaptor<Player> playerCaptor = ArgumentCaptor.forClass(Player.class);
        given(service.create(any(Player.class))).willReturn(player);

        controller.create(document);

        verify(service).create(playerCaptor.capture());
        assertThat(playerCaptor.getValue()).isEqualToComparingFieldByFieldRecursively(player);
    }

    @Test
    public void shouldConverterCreatedPlayerIntoDocument() {
        given(service.create(any(Player.class))).willReturn(player);

        final ResponseEntity<PlayerDocument> entity = controller.create(document);

        assertThat(entity.getBody()).isEqualToComparingFieldByFieldRecursively(document);
    }

    @Test
    public void shouldReturnCreatedStatusCodeOnCreate() {
        given(service.create(any(Player.class))).willReturn(player);

        final ResponseEntity<PlayerDocument> entity = controller.create(document);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void shouldReturnOkStatusCodeOnCreateWithIdThatAlreadyExists() {
        given(service.exists(player.getId())).willReturn(true);
        given(service.update(any(Player.class))).willReturn(player);

        final ResponseEntity<PlayerDocument> entity = controller.create(document);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReturnLocationHeaderWithNewResourceUrlOnCreate() {
        given(service.create(any(Player.class))).willReturn(player);

        final ResponseEntity<PlayerDocument> entity = controller.create(document);

        final HttpHeaders headers = entity.getHeaders();

        assertThat(headers.get("Location")).containsExactly("/players/1133");
    }

    @Test
    public void shouldConvertDocumentToPlayerOnUpdate() {
        final ArgumentCaptor<Player> playerCaptor = ArgumentCaptor.forClass(Player.class);
        given(service.update(any(Player.class))).willReturn(player);

        controller.update(document.getId(), document);

        verify(service).update(playerCaptor.capture());
        assertThat(playerCaptor.getValue()).isEqualToComparingFieldByFieldRecursively(player);
    }

    @Test
    public void shouldConvertUpdatedPlayerIntoDocumentOnUpdate() {
        given(service.update(any(Player.class))).willReturn(player);

        final ResponseEntity<PlayerDocument> entity = controller.update(document.getId(), document);

        assertThat(entity.getBody()).isEqualToComparingFieldByFieldRecursively(document);
    }

    @Test
    public void shouldReturnOkStatusOnUpdate() {
        given(service.update(any(Player.class))).willReturn(player);

        final ResponseEntity<PlayerDocument> entity = controller.update(document.getId(), document);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReadPlayer() {
        given(service.read(document.getId())).willReturn(player);

        final PlayerDocument resultDocument = controller.read(document.getId());

        assertThat(resultDocument).isEqualToComparingFieldByFieldRecursively(document);
    }


    @Test
    public void shouldReadPlayers() {
        final PlayersDocument expectedDocument = new PlayersDocument();
        final Page<Player> page = toPage(expectedDocument);
        final int pageNumber = expectedDocument.getPageNumber();
        final int pageSize = expectedDocument.getPageSize();
        given(service.read(pageNumber, pageSize)).willReturn(page);

        final ArrayDocument<PlayerData> resultDocument = controller.read(pageNumber, pageSize);

        assertThat(resultDocument).isEqualToComparingFieldByFieldRecursively(expectedDocument);
    }

    private static Player toPlayer(PlayerData data) {
        Player player = new Player();
        player.setId(data.getId());
        player.setFirstName(data.getFirstName());
        player.setLastName(data.getLastName());
        player.setPosition(Position.valueOf(data.getPosition()));
        player.setValue(data.getValue());
        if (data.getClubId().isPresent()) {
            player.setClub(new Club(data.getClubId().get()));
        }
        return player;
    }

    private static Page<Player> toPage(final PlayersDocument document) {
        return buildEmptyPlayerPage(document.getPageNumber(), document.getPageSize(), document.getTotalPages());
    }

    private static Page<Player> buildEmptyPlayerPage(int pageNumber, int pageSize, long totalPages) {
        return new PageImpl<>(emptyList(), PageRequest.of(pageNumber, pageSize), totalPages);
    }

}
