package uk.co.mruoc.fantasyfootball.app.web;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.co.mruoc.fantasyfootball.api.ArrayDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument.PlayerData;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument.PlayerDocumentBuilder;
import uk.co.mruoc.fantasyfootball.api.PlayersDocument;
import uk.co.mruoc.fantasyfootball.app.dao.Player;
import uk.co.mruoc.fantasyfootball.app.service.PlayerService;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class PlayerControllerTest {

    private static final long ID = 1133;
    private static final String FIRST_NAME = "Joe";
    private static final String LAST_NAME = "Bloggs";
    private static final String POSITION = "DEFENDER";
    private static final Integer VALUE = 50000000;
    private static final String SELF_LINK = String.format("/players/%s", ID);
    private static final long CLUB_ID = 1234;

    private final PlayerService service = mock(PlayerService.class);
    private final PlayerConverter converter = mock(PlayerConverter.class);

    private final PlayerDocument document = buildPlayerDocument();
    private final Player player = new Player();

    private final PlayerController controller = new PlayerController(service, converter);

    @Test
    public void shouldCreatePlayer() {
        given(converter.toPlayer(document)).willReturn(player);
        final Player createdPlayer = new Player();
        given(service.create(player)).willReturn(createdPlayer);
        final PlayerDocument createdDocument = buildPlayerDocument();
        given(converter.toDocument(createdPlayer)).willReturn(createdDocument);

        final ResponseEntity<PlayerDocument> entity = controller.create(document);

        assertThat(entity.getBody()).isEqualTo(createdDocument);
    }

    @Test
    public void shouldReturnCreatedStatusCodeOnCreate() {
        given(converter.toPlayer(document)).willReturn(player);
        given(service.create(player)).willReturn(player);
        given(converter.toDocument(player)).willReturn(document);

        final ResponseEntity<PlayerDocument> entity = controller.create(document);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void shouldReturnOkStatusCodeOnCreateWithIdThatAlreadyExists() {
        given(converter.toPlayer(document)).willReturn(player);
        given(service.exists(ID)).willReturn(true);
        given(service.update(player)).willReturn(player);
        given(converter.toDocument(player)).willReturn(document);

        final ResponseEntity<PlayerDocument> entity = controller.create(document);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReturnLocationHeaderWithNewResourceUrlOnCreate() {
        given(converter.toPlayer(document)).willReturn(player);
        given(service.create(player)).willReturn(player);
        given(converter.toDocument(player)).willReturn(document);

        final ResponseEntity<PlayerDocument> entity = controller.create(document);

        final HttpHeaders headers = entity.getHeaders();
        assertThat(headers.get("Location")).containsExactly("/players/1133");
    }

    @Test
    public void shouldUpdatePlayer() {
        given(converter.toPlayer(ID, document)).willReturn(player);
        final Player updatedPlayer = new Player();
        given(service.update(player)).willReturn(updatedPlayer);
        final PlayerDocument updatedDocument = buildPlayerDocument();
        given(converter.toDocument(updatedPlayer)).willReturn(updatedDocument);

        final ResponseEntity<PlayerDocument> entity = controller.update(ID, document);

        assertThat(entity.getBody()).isEqualTo(updatedDocument);
    }

    @Test
    public void shouldReturnOkStatusOnUpdate() {
        given(converter.toPlayer(document)).willReturn(player);
        given(service.update(player)).willReturn(player);
        given(converter.toDocument(player)).willReturn(document);

        final ResponseEntity<PlayerDocument> entity = controller.update(document.getId(), document);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReadPlayer() {
        given(service.read(document.getId())).willReturn(player);
        final PlayerDocument readDocument = new PlayerDocument();
        given(converter.toDocument(player)).willReturn(readDocument);

        final PlayerDocument resultDocument = controller.read(document.getId());

        assertThat(resultDocument).isEqualTo(readDocument);
    }

    @Test
    public void shouldReadPlayers() {
        final int pageNumber = 0;
        final int pageSize = 10;
        final int totalPages = 1;
        final Page<Player> page = new PageImpl<>(emptyList(), PageRequest.of(pageNumber, pageSize), totalPages);
        given(service.read(pageNumber, pageSize)).willReturn(page);
        final PlayersDocument expectedDocument = new PlayersDocument();
        given(converter.toPlayersDocument(page)).willReturn(expectedDocument);

        final ArrayDocument<PlayerData> resultDocument = controller.read(pageNumber, pageSize);

        assertThat(resultDocument).isEqualTo(expectedDocument);
    }

    private static PlayerDocument buildPlayerDocument() {
        return new PlayerDocumentBuilder()
                .setId(ID)
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setPosition(POSITION)
                .setValue(VALUE)
                .setSelfLink(SELF_LINK)
                .setClubId(CLUB_ID)
                .build();
    }

}
