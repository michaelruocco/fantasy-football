package uk.co.mruoc.fantasyfootball.app.web;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument.PlayerDocumentBuilder;
import uk.co.mruoc.fantasyfootball.app.dao.Player;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerConverterTest {

    private static final long ID = 1133;
    private static final String FIRST_NAME = "Joe";
    private static final String LAST_NAME = "Bloggs";
    private static final String POSITION = "DEFENDER";
    private static final Integer VALUE = 50000000;
    private static final long CLUB_ID = 1234;

    private final PlayerConverter converter = new PlayerConverter();


    @Test
    public void shouldConvertIdFromDocument() {
        final PlayerDocument document = buildPlayerDocument();

        final Player player = converter.toPlayer(document);

        assertThat(player.getId()).isEqualTo(document.getId());
    }

    @Test
    public void shouldConvertSpecifiedId() {
        final long id = 9191;
        final PlayerDocument document = buildPlayerDocument();

        final Player player = converter.toPlayer(id, document);

        assertThat(player.getId()).isEqualTo(id);
    }

    @Test
    public void shouldConvertNameFromDocument() {
        final PlayerDocument document = buildPlayerDocument();

        final Player player = converter.toPlayer(document);

        assertThat(player.getFirstName()).isEqualTo(document.getFirstName());
    }

    @Test
    public void shouldConvertLastNameFromDocument() {
        final PlayerDocument document = buildPlayerDocument();

        final Player player = converter.toPlayer(document);

        assertThat(player.getLastName()).isEqualTo(document.getLastName());
    }

    @Test
    public void shouldConvertPositionFromDocument() {
        final PlayerDocument document = buildPlayerDocument();

        final Player player = converter.toPlayer(document);

        assertThat(player.getPosition().name()).isEqualTo(document.getPosition());
    }

    @Test
    public void shouldConvertValueFromDocument() {
        final PlayerDocument document = buildPlayerDocument();

        final Player player = converter.toPlayer(document);

        assertThat(player.getValue()).isEqualTo(document.getValue());
    }

    @Test
    public void shouldConvertClubIdFromDocument() {
        final PlayerDocument document = buildPlayerDocument();

        final Player player = converter.toPlayer(document);

        assertThat(player.getClubId()).isEqualTo(document.getClubId());
    }

    private static PlayerDocument buildPlayerDocument() {
        return new PlayerDocumentBuilder()
                .setId(ID)
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setPosition(POSITION)
                .setValue(VALUE)
                .setClubId(CLUB_ID)
                .build();
    }

}
