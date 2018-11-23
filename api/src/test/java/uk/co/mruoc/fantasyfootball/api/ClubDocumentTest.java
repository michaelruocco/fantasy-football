package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.ClubDocument.ClubDocumentBuilder;
import uk.co.mruoc.file.ContentLoader;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ClubDocumentTest {

    private static final String JSON_PATH = "/clubDocument.json";
    private static final String JSON = ContentLoader.loadContentFromClasspath(JSON_PATH);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final long ID = 1234;
    private static final String NAME = "Fake Club 1";
    private static final String CLUB_PLAYERS_LINK = String.format("/clubs/%s/players?pageNumber=0&pageSize=10", ID);
    private static final String SELF_LINK = String.format("/clubs/%s", ID);

    @Test
    public void shouldSerializeToJsonCorrectly() throws JsonProcessingException {
        final ClubDocument document = buildClubDocument();

        final String json = MAPPER.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(JSON);
    }

    @Test
    public void shouldDeserializeFromJsonCorrectly() throws IOException {
        final ClubDocument expectedDocument = buildClubDocument();

        final ClubDocument document = MAPPER.readValue(JSON, ClubDocument.class);

        assertThat(document).isEqualToComparingFieldByFieldRecursively(expectedDocument);
    }

    @Test
    public void shouldReturnId() {
        final ClubDocument document = buildClubDocument();

        assertThat(document.getId()).isEqualTo(ID);
    }

    @Test
    public void shouldReturnTrueIfHasId() {
        final ClubDocument document = buildClubDocument();

        assertThat(document.hasId()).isTrue();
    }

    @Test
    public void shouldReturnFalseIfDoesNotHaveId() {
        final ClubDocument document = new ClubDocument();

        assertThat(document.hasId()).isFalse();
    }

    @Test
    public void shouldReturnName() {
        final ClubDocument document = buildClubDocument();

        assertThat(document.getName()).isEqualTo(NAME);
    }

    @Test
    public void shouldReturnSelfLink() {
        final ClubDocument document = buildClubDocument();

        assertThat(document.getSelfLink()).isEqualTo(SELF_LINK);
    }

    @Test
    public void shouldReturnClubPlayersLink() {
        final ClubDocument document = buildClubDocument();

        assertThat(document.getClubPlayersLink()).isEqualTo(CLUB_PLAYERS_LINK);
    }

    private static ClubDocument buildClubDocument() {
        return new ClubDocumentBuilder()
                .setId(ID)
                .setName(NAME)
                .setClubPlayersLink(CLUB_PLAYERS_LINK)
                .setSelfLink(SELF_LINK)
                .build();
    }

}