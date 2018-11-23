package uk.co.mruoc.fantasyfootball.app.web;

import org.junit.Ignore;
import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.fantasyfootball.app.dao.Player;

import static org.assertj.core.api.Assertions.assertThat;

@Ignore
public class PlayerConverterTest {

    private final PlayerConverter converter = new PlayerConverter();


    @Test
    public void shouldConvertIdFromDocument() {
        final PlayerDocument document = new PlayerDocument();

        final Player player = converter.toPlayer(document);

        assertThat(player.getId()).isEqualTo(document.getId());
    }

    @Test
    public void shouldConvertSpecificId() {
        final long id = 9191;
        final PlayerDocument document = new PlayerDocument();

        final Player player = converter.toPlayer(id, document);

        assertThat(player.getId()).isEqualTo(id);
    }

    @Test
    public void shouldConvertNameFromDocument() {
        final PlayerDocument document = new PlayerDocument();

        final Player player = converter.toPlayer(document);

        assertThat(player.getFirstName()).isEqualTo(document.getFirstName());
    }

    @Test
    public void shouldConvertLastNameFromDocument() {
        final PlayerDocument document = new PlayerDocument();

        final Player player = converter.toPlayer(document);

        assertThat(player.getLastName()).isEqualTo(document.getLastName());
    }

    @Test
    public void shouldConvertPositionFromDocument() {
        final PlayerDocument document = new PlayerDocument();

        final Player player = converter.toPlayer(document);

        assertThat(player.getPosition().name()).isEqualTo(document.getPosition());
    }

    @Test
    public void shouldConvertValueFromDocument() {
        final PlayerDocument document = new PlayerDocument();

        final Player player = converter.toPlayer(document);

        assertThat(player.getValue()).isEqualTo(document.getValue());
    }

    @Test
    public void shouldConvertClubIdFromDocument() {
        final PlayerDocument document = new PlayerDocument();

        final Player player = converter.toPlayer(document);

        assertThat(player.getClubId()).isEqualTo(document.getClubId());
    }

}
