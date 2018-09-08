package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.example.ExamplePlayerDocumentFactory;
import uk.co.mruoc.file.ContentLoader;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ClubPlayersDocumentTest {

    private final ObjectMapper mapper = JacksonMapperSingleton.get();

    @Test
    public void shouldSerializeToJsonCorrectly() throws JsonProcessingException  {
        final String expectedJson = ContentLoader.loadContentFromClasspath("/playersDocument.json");
        final ClubPlayersDocument document = ExamplePlayerDocumentFactory.buildClubPlayersDocument();

        final String json = mapper.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    public void shouldSerializeToJsonCorrectlyWhenPartOfMultiplePages() throws JsonProcessingException  {
        final String expectedJson = ContentLoader.loadContentFromClasspath("/playersDocumentWithMultiplePages.json");
        final ClubPlayersDocument document = ExamplePlayerDocumentFactory.buildClubPlayersDocumentWithMultiplePages();

        final String json = mapper.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    public void shouldSerializeToJsonCorrectlyWhenNoDataPresent() throws JsonProcessingException  {
        final String expectedJson = ContentLoader.loadContentFromClasspath("/playersDocumentWithNoData.json");
        final ClubPlayersDocument document = ExamplePlayerDocumentFactory.buildClubPlayersDocumentWithNoData();

        final String json = mapper.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    public void shouldDeserializeFromJsonCorrectly() throws IOException {
        final String json = ContentLoader.loadContentFromClasspath("/playersDocument.json");
        final ClubPlayersDocument expectedDocument = ExamplePlayerDocumentFactory.buildClubPlayersDocument();

        final ClubPlayersDocument document = mapper.readValue(json, ClubPlayersDocument.class);

        assertThat(document).isEqualToComparingFieldByFieldRecursively(expectedDocument);
    }

    @Test
    public void shouldReturnClubId() {
        final ClubPlayersDocument document = ExamplePlayerDocumentFactory.buildClubPlayersDocument();

        assertThat(document.getClubId()).isEqualTo(1234);
    }

    @Test
    public void shouldReturnPageNumber() {
        final ClubPlayersDocument document = ExamplePlayerDocumentFactory.buildClubPlayersDocument();

        assertThat(document.getPageNumber()).isEqualTo(0);
    }

    @Test
    public void shouldReturnPageSize() {
        final ClubPlayersDocument document = ExamplePlayerDocumentFactory.buildClubPlayersDocument();

        assertThat(document.getPageSize()).isEqualTo(2);
    }

    @Test
    public void shouldReturnTotalNumberOfPages() {
        final ClubPlayersDocument document = ExamplePlayerDocumentFactory.buildClubPlayersDocument();

        assertThat(document.getTotalPages()).isEqualTo(1);
    }

    @Test
    public void shouldReturnTotalPlayers() {
        final ClubPlayersDocument document = ExamplePlayerDocumentFactory.buildClubPlayersDocument();

        assertThat(document.getTotalPlayers()).isEqualTo(2);
    }

}