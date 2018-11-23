package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument.PlayerData;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument.PlayerDocumentBuilder;
import uk.co.mruoc.file.ContentLoader;

import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayersDocumentTest {

    private static final String JSON_PATH = "/playersDocument.json";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String JSON = ContentLoader.loadContentFromClasspath(JSON_PATH);

    private static final long ID_1 = 1133;
    private static final String FIRST_NAME_1 = "Joe";
    private static final String LAST_NAME_1 = "Bloggs";
    private static final String POSITION_1 = "DEFENDER";
    private static final int VALUE_1 = 50000000;
    private static final String SELF_LINK_1 = String.format("/players/%s", ID_1);
    private static final long CLUB_ID_1 = 1234;
    private static final String CLUB_LINK_1 = String.format("/clubs/%s", CLUB_ID_1);


    private static final long ID_2 = 1122;
    private static final String FIRST_NAME_2 = "Michael";
    private static final String LAST_NAME_2 = "Ruocco";
    private static final String POSITION_2 = "STRIKER";
    private static final int VALUE_2 = 10000000;
    private static final String SELF_LINK_2 = String.format("/players/%s", ID_2);
    private static final long CLUB_ID_2 = 1234;
    private static final String CLUB_LINK_2 = String.format("/clubs/%s", CLUB_ID_2);

    private static final int PAGE_NUMBER = 3;
    private static final int PAGE_SIZE = 4;
    private static final int TOTAL_ITEMS = 2;
    private static final int TOTAL_PAGES = 1;

    @Test
    public void shouldSerializeToJsonCorrectly() throws JsonProcessingException {
        final ArrayDocument<PlayerData> document = buildPlayersDocument();

        final String json = MAPPER.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(JSON);
    }

    @Test
    public void shouldDeserializeFromJsonCorrectly() throws IOException {
        final ArrayDocument<PlayerData> expectedDocument = buildPlayersDocument();

        final PlayersDocument document = MAPPER.readValue(JSON, PlayersDocument.class);

        assertThat(document).isEqualToComparingFieldByFieldRecursively(expectedDocument);
    }

    @Test
    public void shouldReturnPageNumber() {
        final ArrayDocument<PlayerData> document = buildPlayersDocument();

        assertThat(document.getPageNumber()).isEqualTo(PAGE_NUMBER);
    }

    @Test
    public void shouldReturnPageSize() {
        final ArrayDocument<PlayerData> document = buildPlayersDocument();

        assertThat(document.getPageSize()).isEqualTo(PAGE_SIZE);
    }

    @Test
    public void shouldReturnTotalNumberOfPages() {
        final ArrayDocument<PlayerData> document = buildPlayersDocument();

        assertThat(document.getTotalPages()).isEqualTo(TOTAL_PAGES);
    }

    @Test
    public void shouldReturnTotalPlayers() {
        final ArrayDocument<PlayerData> document = buildPlayersDocument();

        assertThat(document.getTotalItems()).isEqualTo(TOTAL_ITEMS);
    }

    private static ArrayDocument<PlayerData> buildPlayersDocument() {
        PlayerDocument player1 = getPlayer1();
        PlayerDocument player2 = getPlayer2();
        return new PlayersDocument.PlayersDocumentBuilder()
                .setData(Arrays.asList(player1.getData(), player2.getData()))
                .setSelfLink("/self")
                .setFirstLink("/first")
                .setLastLink("/last")
                .setNextLink("/next")
                .setPreviousLink("/previous")
                .setPageNumber(PAGE_NUMBER)
                .setPageSize(PAGE_SIZE)
                .setTotalItems(TOTAL_ITEMS)
                .setTotalPages(TOTAL_PAGES)
                .build();
    }

    private static PlayerDocument getPlayer1() {
        return new PlayerDocumentBuilder()
                .setId(ID_1)
                .setFirstName(FIRST_NAME_1)
                .setLastName(LAST_NAME_1)
                .setPosition(POSITION_1)
                .setValue(VALUE_1)
                .setSelfLink(SELF_LINK_1)
                .setClubId(CLUB_ID_1)
                .setClubLink(CLUB_LINK_1)
                .build();
    }

    private static PlayerDocument getPlayer2() {
        return new PlayerDocumentBuilder()
                .setId(ID_2)
                .setFirstName(FIRST_NAME_2)
                .setLastName(LAST_NAME_2)
                .setPosition(POSITION_2)
                .setValue(VALUE_2)
                .setSelfLink(SELF_LINK_2)
                .setClubId(CLUB_ID_2)
                .setClubLink(CLUB_LINK_2)
                .build();
    }

}