package uk.co.mruoc.fantasyfootball.web;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import uk.co.mruoc.fantasyfootball.FakePlayerFactory;
import uk.co.mruoc.fantasyfootball.api.ClubPlayersDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.fantasyfootball.dao.Player;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerConverterTest {

    private final PlayerConverter converter = new PlayerConverter();

    @Test
    public void shouldConvertIdToDocument() {
        final  Player player = FakePlayerFactory.buildPlayer1();

        final PlayerDocument document = converter.toDocument(player);

        assertThat(document.getId()).isEqualTo(player.getId());
    }

    @Test
    public void shouldConvertFirstNameToDocument() {
        final Player player = FakePlayerFactory.buildPlayer1();

        final PlayerDocument document = converter.toDocument(player);

        assertThat(document.getFirstName()).isEqualTo(player.getFirstName());
    }

    @Test
    public void shouldConvertLastNameToDocument() {
        final  Player player = FakePlayerFactory.buildPlayer1();

        final PlayerDocument document = converter.toDocument(player);

        assertThat(document.getLastName()).isEqualTo(player.getLastName());
    }

    @Test
    public void shouldConvertPositionToDocument() {
        final Player player = FakePlayerFactory.buildPlayer1();

        final PlayerDocument document = converter.toDocument(player);

        assertThat(document.getPosition()).isEqualTo(player.getPosition().name());
    }

    @Test
    public void shouldConvertValueToDocument() {
        final Player player = FakePlayerFactory.buildPlayer1();

        final PlayerDocument document = converter.toDocument(player);

        assertThat(document.getValue()).isEqualTo(player.getValue());
    }

    @Test
    public void shouldConvertClubIdToDocument() {
        final Player player = FakePlayerFactory.buildPlayer1();

        final PlayerDocument document = converter.toDocument(player);

        assertThat(document.getClubId()).isEqualTo(player.getClubId());
    }

    @Test
    public void shouldConvertIdFromDocument() {
        final PlayerDocument document = FakePlayerFactory.buildPlayerDocument1();

        final Player player = converter.toPlayer(document);

        assertThat(player.getId()).isEqualTo(document.getId());
    }

    @Test
    public void shouldConvertSpecificId() {
        final long id = 9191;
        final PlayerDocument document = FakePlayerFactory.buildPlayerDocument1();

        final Player player = converter.toPlayer(id, document);

        assertThat(player.getId()).isEqualTo(id);
    }

    @Test
    public void shouldConvertNameFromDocument() {
        final PlayerDocument document = FakePlayerFactory.buildPlayerDocument1();

        final Player player = converter.toPlayer(document);

        assertThat(player.getFirstName()).isEqualTo(document.getFirstName());
    }

    @Test
    public void shouldConvertLastNameFromDocument() {
        final PlayerDocument document = FakePlayerFactory.buildPlayerDocument1();

        final Player player = converter.toPlayer(document);

        assertThat(player.getLastName()).isEqualTo(document.getLastName());
    }

    @Test
    public void shouldConvertPositionFromDocument() {
        final PlayerDocument document = FakePlayerFactory.buildPlayerDocument1();

        final Player player = converter.toPlayer(document);

        assertThat(player.getPosition().name()).isEqualTo(document.getPosition());
    }

    @Test
    public void shouldConvertValueFromDocument() {
        final PlayerDocument document = FakePlayerFactory.buildPlayerDocument1();

        final Player player = converter.toPlayer(document);

        assertThat(player.getValue()).isEqualTo(document.getValue());
    }

    @Test
    public void shouldConvertClubIdFromDocument() {
        final PlayerDocument document = FakePlayerFactory.buildPlayerDocument1();

        final Player player = converter.toPlayer(document);

        assertThat(player.getClubId()).isEqualTo(document.getClubId());
    }

    @Test
    public void shouldConvertPageOfPlayersIntoClubPlayersDocument() {
        final long clubId = 8282;
        final Page<Player> page = new PageImpl<>(FakePlayerFactory.buildPlayers());

        final ClubPlayersDocument document = converter.toDocument(clubId, page);

        assertThat(document).isEqualToComparingFieldByFieldRecursively(FakePlayerFactory.buildClubPlayersDocument());
    }

}
