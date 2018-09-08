package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.example.ClubData;
import uk.co.mruoc.fantasyfootball.api.example.ExampleClubDocumentFactory;
import uk.co.mruoc.fantasyfootball.api.example.ExampleClubData1;
import uk.co.mruoc.file.ContentLoader;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ClubDocumentTest {

    private static final ClubData CLUB_DATA = new ExampleClubData1();

    private static final String JSON = ContentLoader.loadContentFromClasspath("/clubDocument.json");

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldSerializeToJsonCorrectly() throws JsonProcessingException  {
        final ClubDocument document = ExampleClubDocumentFactory.buildClubDocument1();

        final String json = mapper.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(JSON);
    }

    @Test
    public void shouldDeserializeFromJsonCorrectly() throws IOException {
        final ClubDocument expectedDocument = ExampleClubDocumentFactory.buildClubDocument1();

        final ClubDocument document = mapper.readValue(JSON, ClubDocument.class);

        assertThat(document).isEqualToComparingFieldByFieldRecursively(expectedDocument);
    }

    @Test
    public void shouldReturnId() {
        final ClubDocument document = ExampleClubDocumentFactory.buildClubDocument(CLUB_DATA);

        assertThat(document.getId()).isEqualTo(CLUB_DATA.getId());
    }

    @Test
    public void shouldReturnName() {
        final ClubDocument document = ExampleClubDocumentFactory.buildClubDocument1();

        assertThat(document.getName()).isEqualTo(CLUB_DATA.getName());
    }

    @Test
    public void shouldReturnSelfLink() {
        final ClubDocument document = ExampleClubDocumentFactory.buildClubDocument1();

        assertThat(document.getSelfLink()).isEqualTo("/clubs/" + document.getId());
    }

}