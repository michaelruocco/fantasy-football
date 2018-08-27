package uk.co.mruoc.fantasyfootball.web;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.fantasyfootball.dao.Player;
import uk.co.mruoc.fantasyfootball.service.PlayerService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class PlayerControllerTest {

    private final PlayerService service = mock(PlayerService.class);
    private final PlayerConverter converter = mock(PlayerConverter.class);

    private final PlayerDocument document = new PlayerDocument();
    private final Player player = new Player();

    private PlayerController controller = new PlayerController(service, converter);

    @Test
    public void shouldCreatePlayer() {
        Player createdPlayer = new Player();
        PlayerDocument expectedDocument = new PlayerDocument();

        given(converter.toPlayer(document)).willReturn(player);
        given(service.upsert(player)).willReturn(createdPlayer);
        given(converter.toDocument(createdPlayer)).willReturn(expectedDocument);

        PlayerDocument resultDocument = controller.create(document);

        assertThat(resultDocument).isEqualTo(expectedDocument);
    }

    @Test
    public void shouldUpdatePlayer() {
        long id = 1212;
        Player updatedPlayer = new Player();
        PlayerDocument expectedDocument = new PlayerDocument();

        given(converter.toPlayer(id, document)).willReturn(player);
        given(service.update(player)).willReturn(updatedPlayer);
        given(converter.toDocument(updatedPlayer)).willReturn(expectedDocument);

        PlayerDocument resultDocument = controller.update(id, document);

        assertThat(resultDocument).isEqualTo(expectedDocument);
    }

    @Test
    public void shouldReadPlayer() {
        long id = 1212;
        PlayerDocument expectedDocument = new PlayerDocument();

        given(service.read(id)).willReturn(player);
        given(converter.toDocument(player)).willReturn(expectedDocument);

        PlayerDocument resultDocument = controller.read(id);

        assertThat(resultDocument).isEqualTo(expectedDocument);
    }


}
