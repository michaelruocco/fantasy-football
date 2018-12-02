package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.ClubDocument.ClubData;
import uk.co.mruoc.fantasyfootball.api.ClubDocument.ClubDocumentBuilder;
import uk.co.mruoc.file.ContentLoader;

import java.io.IOException;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ClubsDocumentTest {

    private static final String JSON_PATH = "/clubsDocument.json";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String JSON = ContentLoader.loadContentFromClasspath(JSON_PATH);

    private static final long ID_1 = 1234;
    private static final String NAME_1 = "Fake Club 1";
    private static final String CLUB_PLAYERS_LINK_1 = String.format("/clubs/%s/players?pageNumber=0&pageSize=10", ID_1);
    private static final String SELF_LINK_1 = String.format("/clubs/%s", ID_1);

    private static final long ID_2 = 2222;
    private static final String NAME_2 = "Fake Club 2";
    private static final String CLUB_PLAYERS_LINK_2 = String.format("/clubs/%s/players?pageNumber=0&pageSize=10", ID_2);
    private static final String SELF_LINK_2 = String.format("/clubs/%s", ID_2);

    private static final int PAGE_NUMBER = 3;
    private static final int PAGE_SIZE = 4;
    private static final int TOTAL_ITEMS = 2;
    private static final int TOTAL_PAGES = 1;

    @Test
    public void shouldSerializeToJsonCorrectly() throws JsonProcessingException {
        final ArrayDocument<ClubData> document = buildClubsDocument();

        final String json = MAPPER.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(JSON);
    }

    @Test
    public void shouldDeserializeFromJsonCorrectly() throws IOException {
        final ArrayDocument<ClubData> expectedDocument = buildClubsDocument();

        final ClubsDocument document = MAPPER.readValue(JSON, ClubsDocument.class);

        assertThat(document).isEqualToComparingFieldByFieldRecursively(expectedDocument);
    }

    @Test
    public void shouldReturnPageNumber() {
        final ArrayDocument<ClubData> document = buildClubsDocument();

        assertThat(document.getPageNumber()).isEqualTo(PAGE_NUMBER);
    }

    @Test
    public void shouldReturnPageSize() {
        final ArrayDocument<ClubData> document = buildClubsDocument();

        assertThat(document.getPageSize()).isEqualTo(PAGE_SIZE);
    }

    @Test
    public void shouldReturnTotalItems() {
        final ArrayDocument<ClubData> document = buildClubsDocument();

        assertThat(document.getTotalItems()).isEqualTo(TOTAL_ITEMS);
    }

    @Test
    public void shouldReturnTotalNumberOfPages() {
        final ArrayDocument<ClubData> document = buildClubsDocument();

        assertThat(document.getTotalPages()).isEqualTo(TOTAL_PAGES);
    }

    private static ArrayDocument<ClubData> buildClubsDocument() {
        ClubDocument club1 = getClub1();
        ClubDocument club2 = getClub2();
        return new ClubsDocument.ClubsDocumentBuilder()
                .setData(Arrays.asList(club1.getData(), club2.getData()))
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

    private static ClubDocument getClub1() {
        return new ClubDocumentBuilder()
                .setId(ID_1)
                .setName(NAME_1)
                .setClubPlayersLink(CLUB_PLAYERS_LINK_1)
                .setSelfLink(SELF_LINK_1)
                .build();
    }

    private static ClubDocument getClub2() {
        return new ClubDocumentBuilder()
                .setId(ID_2)
                .setName(NAME_2)
                .setClubPlayersLink(CLUB_PLAYERS_LINK_2)
                .setSelfLink(SELF_LINK_2)
                .build();
    }

}