package uk.co.mruoc.fantasyfootball.app.web;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import uk.co.mruoc.fantasyfootball.api.ArrayDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument.PlayerData;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument.PlayerDocumentBuilder;
import uk.co.mruoc.fantasyfootball.app.dao.Club;
import uk.co.mruoc.fantasyfootball.app.dao.Player;
import uk.co.mruoc.fantasyfootball.app.dao.Position;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
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

    @Test
    public void shouldNotConvertClubIdFromDocumentIfClubIdNotPresent() {
        final PlayerDocument document = buildPlayerDocumentWithoutClub();

        final Player player = converter.toPlayer(document);

        assertThat(player.hasClub()).isFalse();
    }

    @Test
    public void shouldNotSetClubInDocumentIfPlayerDoesNotHaveClub() {
        final Player player = buildPlayer();

        final PlayerDocument document = converter.toDocument(player);

        assertThat(document.hasClub()).isFalse();
    }

    @Test
    public void shouldSetClubInDocumentIfPlayerHasClub() {
        final Player player = buildPlayer();
        player.setClub(new Club(CLUB_ID, "Club Name"));

        final PlayerDocument document = converter.toDocument(player);

        assertThat(document.hasClub()).isTrue();
    }

    @Test
    public void shouldConvertPageToClubPlayersDocument() {
        final int pageNumber = 0;
        final int pageSize = 2;
        final int totalPages = 1;
        final Page<Player> page = new PageImpl<>(emptyList(), PageRequest.of(pageNumber, pageSize), totalPages);

        final ArrayDocument<PlayerData> document = converter.toClubPlayersDocument(CLUB_ID, page);

        assertThat(document.getSelfLink()).isEqualTo("/clubs/1234/players?pageNumber=0&pageSize=2");
        assertThat(document.getNextLink()).isNull();
        assertThat(document.getPreviousLink()).isNull();
        assertThat(document.getFirstLink()).isEqualTo("/clubs/1234/players?pageNumber=0&pageSize=2");
        assertThat(document.getLastLink()).isEqualTo("/clubs/1234/players?pageNumber=0&pageSize=2");
        assertThat(document.getData()).isEmpty();
    }

    @Test
    public void shouldConvertPageToClubPlayersDocumentWithPreviousLinkIfNotFirstPage() {
        final int pageNumber = 1;
        final int pageSize = 2;
        final int totalPages = 1;
        final Page<Player> page = new PageImpl<>(emptyList(), PageRequest.of(pageNumber, pageSize), totalPages);

        final ArrayDocument<PlayerData> document = converter.toClubPlayersDocument(CLUB_ID, page);

        assertThat(document.getPreviousLink()).isEqualTo("/clubs/1234/players?pageNumber=0&pageSize=2");
    }

    @Test
    public void shouldConvertPageToClubPlayersDocumentWithNextLinkIfNotLastPage() {
        final int pageNumber = 0;
        final int pageSize = 2;
        final int totalPages = 3;
        final List<Player> players = Arrays.asList(buildPlayer(), buildPlayer(), buildPlayer(), buildPlayer());
        final Page<Player> page = new PageImpl<>(players, PageRequest.of(pageNumber, pageSize), totalPages);

        final ArrayDocument<PlayerData> document = converter.toClubPlayersDocument(CLUB_ID, page);

        assertThat(document.getNextLink()).isEqualTo("/clubs/1234/players?pageNumber=1&pageSize=2");
    }

    @Test
    public void shouldConvertPageToPlayersDocument() {
        final int pageNumber = 0;
        final int pageSize = 2;
        final int totalPages = 1;
        final Page<Player> page = new PageImpl<>(emptyList(), PageRequest.of(pageNumber, pageSize), totalPages);

        final ArrayDocument<PlayerData> document = converter.toPlayersDocument(page);

        assertThat(document.getSelfLink()).isEqualTo("/players?pageNumber=0&pageSize=2");
        assertThat(document.getNextLink()).isNull();
        assertThat(document.getPreviousLink()).isNull();
        assertThat(document.getFirstLink()).isEqualTo("/players?pageNumber=0&pageSize=2");
        assertThat(document.getLastLink()).isEqualTo("/players?pageNumber=0&pageSize=2");
        assertThat(document.getData()).isEmpty();
    }

    private static Player buildPlayer() {
        final Player player = new Player();
        player.setId(ID);
        player.setPosition(Position.valueOf(POSITION));
        return player;
    }

    private static PlayerDocument buildPlayerDocument() {
        return playerDocumentBuilder()
                .setClubId(CLUB_ID)
                .build();
    }

    private static PlayerDocument buildPlayerDocumentWithoutClub() {
        return playerDocumentBuilder().build();
    }

    private static PlayerDocumentBuilder playerDocumentBuilder() {
        return new PlayerDocumentBuilder()
                .setId(ID)
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setPosition(POSITION)
                .setValue(VALUE);
    }

}
