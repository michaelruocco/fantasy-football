package uk.co.mruoc.fantasyfootball.web;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.co.mruoc.fantasyfootball.FakePlayerFactory;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.fantasyfootball.dao.Player;
import uk.co.mruoc.fantasyfootball.service.PlayerService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PlayerControllerTest {

    private final PlayerService service = mock(PlayerService.class);

    private final PlayerDocument document = FakePlayerFactory.buildPlayerDocument1();
    private final Player player = FakePlayerFactory.buildPlayer1();

    private final PlayerController controller = new PlayerController(service);

    @Test
    public void shouldConvertDocumentToPlayerOnCreate() {
        final ArgumentCaptor<Player> playerCaptor = ArgumentCaptor.forClass(Player.class);
        given(service.upsert(any(Player.class))).willReturn(player);

        controller.create(document);

        verify(service).upsert(playerCaptor.capture());
        assertThat(playerCaptor.getValue()).isEqualToComparingFieldByFieldRecursively(player);
    }

    @Test
    public void shouldConverterCreatedPlayerIntoDocument() {
        given(service.upsert(any(Player.class))).willReturn(player);

        final ResponseEntity<PlayerDocument> entity = controller.create(document);

        assertThat(entity.getBody()).isEqualToComparingFieldByFieldRecursively(document);
    }

    @Test
    public void shouldReturnCreatedStatusCode() {
        given(service.upsert(any(Player.class))).willReturn(player);

        final ResponseEntity<PlayerDocument> entity = controller.create(document);

        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void shouldReturnLocationHeaderWithNewResourceUrl() {
        given(service.upsert(any(Player.class))).willReturn(player);

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
    public void shouldConverterUpdatedPlayerIntoDocument() {
        given(service.update(any(Player.class))).willReturn(player);

        final PlayerDocument resultDocument = controller.update(document.getId(), document);

        assertThat(resultDocument).isEqualToComparingFieldByFieldRecursively(document);
    }

    @Test
    public void shouldReadPlayer() {
        given(service.read(document.getId())).willReturn(player);

        final PlayerDocument resultDocument = controller.read(document.getId());

        assertThat(resultDocument).isEqualToComparingFieldByFieldRecursively(document);
    }

}
