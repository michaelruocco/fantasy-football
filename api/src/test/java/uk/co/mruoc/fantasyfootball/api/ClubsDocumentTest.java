package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.example.ExampleClubDocumentFactory;
import uk.co.mruoc.file.ContentLoader;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ClubsDocumentTest {

    private final ObjectMapper mapper = JacksonMapperSingleton.get();

    @Test
    public void shouldSerializeToJsonCorrectly() throws JsonProcessingException  {
        final String expectedJson = ContentLoader.loadContentFromClasspath("/clubsDocument.json");
        final ClubsDocument document = ExampleClubDocumentFactory.buildClubsDocument();

        final String json = mapper.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    public void shouldSerializeToJsonCorrectlyWhenPartOfMultiplePages() throws JsonProcessingException  {
        final String expectedJson = ContentLoader.loadContentFromClasspath("/clubsDocumentWithMultiplePages.json");
        final ClubsDocument document = ExampleClubDocumentFactory.buildClubsDocumentWithMultiplePages();

        final String json = mapper.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    public void shouldSerializeToJsonCorrectlyWhenNoDataPresent() throws JsonProcessingException  {
        final String expectedJson = ContentLoader.loadContentFromClasspath("/clubsDocumentWithNoData.json");
        final ClubsDocument document = ExampleClubDocumentFactory.buildClubsDocumentWithNoData();

        final String json = mapper.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    public void shouldDeserializeFromJsonCorrectly() throws IOException {
        final String json = ContentLoader.loadContentFromClasspath("/clubsDocument.json");
        final ClubsDocument expectedDocument = ExampleClubDocumentFactory.buildClubsDocument();

        final ClubsDocument document = mapper.readValue(json, ClubsDocument.class);

        assertThat(document).isEqualToComparingFieldByFieldRecursively(expectedDocument);
    }

    @Test
    public void shouldReturnPageNumber() {
        final ClubsDocument document = ExampleClubDocumentFactory.buildClubsDocument();

        assertThat(document.getPageNumber()).isEqualTo(0);
    }

    @Test
    public void shouldReturnPageSize() {
        final ClubsDocument document = ExampleClubDocumentFactory.buildClubsDocument();

        assertThat(document.getPageSize()).isEqualTo(2);
    }

    @Test
    public void shouldReturnTotalNumberOfPages() {
        final ClubsDocument document = ExampleClubDocumentFactory.buildClubsDocument();

        assertThat(document.getTotalPages()).isEqualTo(1);
    }

    @Test
    public void shouldReturnTotalClubs() {
        final ClubsDocument document = ExampleClubDocumentFactory.buildClubsDocument();

        assertThat(document.getTotalClubs()).isEqualTo(2);
    }

}