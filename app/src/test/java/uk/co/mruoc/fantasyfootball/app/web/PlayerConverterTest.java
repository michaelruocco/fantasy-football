package uk.co.mruoc.fantasyfootball.app.web;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import uk.co.mruoc.fantasyfootball.api.ClubPlayersDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument.PlayerData;
import uk.co.mruoc.fantasyfootball.api.example.ExamplePlayerDocumentFactory;
import uk.co.mruoc.fantasyfootball.app.dao.Club;
import uk.co.mruoc.fantasyfootball.app.dao.Player;
import uk.co.mruoc.fantasyfootball.app.dao.Position;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerConverterTest {

    private static final PlayerDocument DOCUMENT = ExamplePlayerDocumentFactory.buildPlayerDocument1();
    private static final Player PLAYER = toPlayer(DOCUMENT.getData());

    private final PlayerConverter converter = new PlayerConverter();

    @Test
    public void shouldConvertIdToDocument() {
        final PlayerDocument document = converter.toDocument(PLAYER);

        assertThat(document.getId()).isEqualTo(PLAYER.getId());
    }

    @Test
    public void shouldConvertFirstNameToDocument() {
        final PlayerDocument document = converter.toDocument(PLAYER);

        assertThat(document.getFirstName()).isEqualTo(PLAYER.getFirstName());
    }

    @Test
    public void shouldConvertLastNameToDocument() {
        final PlayerDocument document = converter.toDocument(PLAYER);

        assertThat(document.getLastName()).isEqualTo(PLAYER.getLastName());
    }

    @Test
    public void shouldConvertPositionToDocument() {
        final PlayerDocument document = converter.toDocument(PLAYER);

        assertThat(document.getPosition()).isEqualTo(PLAYER.getPositionName());
    }

    @Test
    public void shouldConvertValueToDocument() {
        final PlayerDocument document = converter.toDocument(PLAYER);

        assertThat(document.getValue()).isEqualTo(PLAYER.getValue());
    }

    @Test
    public void shouldConvertClubIdToDocument() {
        final PlayerDocument document = converter.toDocument(PLAYER);

        assertThat(document.getClubId()).isEqualTo(PLAYER.getClubId());
    }

    @Test
    public void shouldConvertIdFromDocument() {
        final PlayerDocument document = ExamplePlayerDocumentFactory.buildPlayerDocument1();

        final Player player = converter.toPlayer(document);

        assertThat(player.getId()).isEqualTo(document.getId());
    }

    @Test
    public void shouldConvertSpecificId() {
        final long id = 9191;
        final PlayerDocument document = ExamplePlayerDocumentFactory.buildPlayerDocument1();

        final Player player = converter.toPlayer(id, document);

        assertThat(player.getId()).isEqualTo(id);
    }

    @Test
    public void shouldConvertNameFromDocument() {
        final PlayerDocument document = ExamplePlayerDocumentFactory.buildPlayerDocument1();

        final Player player = converter.toPlayer(document);

        assertThat(player.getFirstName()).isEqualTo(document.getFirstName());
    }

    @Test
    public void shouldConvertLastNameFromDocument() {
        final PlayerDocument document = ExamplePlayerDocumentFactory.buildPlayerDocument1();

        final Player player = converter.toPlayer(document);

        assertThat(player.getLastName()).isEqualTo(document.getLastName());
    }

    @Test
    public void shouldConvertPositionFromDocument() {
        final PlayerDocument document = ExamplePlayerDocumentFactory.buildPlayerDocument1();

        final Player player = converter.toPlayer(document);

        assertThat(player.getPosition().name()).isEqualTo(document.getPosition());
    }

    @Test
    public void shouldConvertValueFromDocument() {
        final PlayerDocument document = ExamplePlayerDocumentFactory.buildPlayerDocument1();

        final Player player = converter.toPlayer(document);

        assertThat(player.getValue()).isEqualTo(document.getValue());
    }

    @Test
    public void shouldConvertClubIdFromDocument() {
        final PlayerDocument document = ExamplePlayerDocumentFactory.buildPlayerDocument1();

        final Player player = converter.toPlayer(document);

        assertThat(player.getClubId()).isEqualTo(document.getClubId());
    }

    @Test
    public void shouldConvertPageOfPlayersIntoClubPlayersDocument() {
        final long clubId = 1234;
        final ClubPlayersDocument expectedDocument = ExamplePlayerDocumentFactory.buildClubPlayersDocument();
        final Page<Player> page = new PageImpl<>(toPlayers(expectedDocument.getData()), PageRequest.of(0, 2), 2);

        final ClubPlayersDocument document = converter.toDocument(clubId, page);

        assertThat(document).isEqualToComparingFieldByFieldRecursively(expectedDocument);
    }

    @Test
    public void shouldSetPreviousLinkWhenConvertingPageOfPlayersIntoClubPlayersDocumentIfNotFirstPage() {
        final long clubId = 1234;
        final ClubPlayersDocument expectedDocument = ExamplePlayerDocumentFactory.buildClubPlayersDocumentWithMultiplePages();
        final Page<Player> page = new PageImpl<>(toPlayers(expectedDocument.getData()), PageRequest.of(1, 2), 6);

        final ClubPlayersDocument document = converter.toDocument(clubId, page);

        assertThat(document).isEqualToComparingFieldByFieldRecursively(expectedDocument);
    }

    private static List<Player> toPlayers(List<PlayerData> playerDataList) {
        return playerDataList.stream().map(PlayerConverterTest::toPlayer).collect(Collectors.toList());
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

}
