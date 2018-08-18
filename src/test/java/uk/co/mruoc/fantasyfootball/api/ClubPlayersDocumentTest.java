package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument.PlayerDocumentBuilder;
import uk.co.mruoc.fantasyfootball.api.ClubPlayersDocument.PlayersDocumentBuilder;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

public class ClubPlayersDocumentTest {

    private static final long CLUB_ID = 2323;

    private final ObjectMapper mapper = JacksonMapperSingleton.get();

    @Before
    public void setup() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));
    }

    @After
    public void teardown() {
        RequestContextHolder.resetRequestAttributes();
    }

    @Test
    public void shouldSerializeToJsonCorrectly() throws JsonProcessingException  {
        String expectedJson = FileContentLoader.load("/playersDocument.json");
        ClubPlayersDocument document = buildPlayersDocument();

        String json = mapper.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    public void shouldSerializeToJsonCorrectlyWhenPartOfMultiplePages() throws JsonProcessingException  {
        String expectedJson = FileContentLoader.load("/playersDocumentWithMultiplePages.json");
        ClubPlayersDocument document = buildPlayersDocument(1, 2, 6, 3, buildPlayerData());

        String json = mapper.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    public void shouldSerializeToJsonCorrectlyWhenNoDataPresent() throws JsonProcessingException  {
        String expectedJson = FileContentLoader.load("/playersDocumentWithNoData.json");
        ClubPlayersDocument document = buildPlayersDocument(0, 2, 0, 0, emptyList());

        String json = mapper.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    public void shouldDeserializeFromJsonCorrectly() throws IOException {
        ClubPlayersDocument expectedDocument = buildPlayersDocument();
        String json = FileContentLoader.load("/playersDocument.json");

        ClubPlayersDocument document = mapper.readValue(json, ClubPlayersDocument.class);

        assertThat(document).isEqualToComparingFieldByFieldRecursively(expectedDocument);
    }

    private static ClubPlayersDocument buildPlayersDocument() {
        return buildPlayersDocument(0, 2,2, 1, buildPlayerData());
    }

    private static List<PlayerDocument.Data> buildPlayerData() {
        return Arrays.asList(
                new PlayerDocumentBuilder()
                        .setId(1122L)
                        .setFirstName("Michael")
                        .setLastName("Ruocco")
                        .setPosition("STRIKER")
                        .setValue(10000000)
                        .setClubId(CLUB_ID)
                        .build()
                        .getData(),
                new PlayerDocumentBuilder()
                        .setId(1133L)
                        .setFirstName("Joe")
                        .setLastName("Bloggs")
                        .setPosition("DEFENDER")
                        .setValue(500000)
                        .setClubId(CLUB_ID)
                        .build()
                        .getData()
        );
    }

    private static ClubPlayersDocument buildPlayersDocument(int pageNumber, int pageSize, int totalPlayers, int totalPages, List<PlayerDocument.Data> players) {
        return new PlayersDocumentBuilder()
                .setClubId(CLUB_ID)
                .setData(players)
                .setTotalPlayers(totalPlayers)
                .setPageNumber(pageNumber)
                .setPageSize(pageSize)
                .setTotalPages(totalPages)
                .build();
    }

}