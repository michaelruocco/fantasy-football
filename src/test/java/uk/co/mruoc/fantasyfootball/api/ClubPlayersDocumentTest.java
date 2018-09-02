package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import uk.co.mruoc.fantasyfootball.FakePlayerFactory;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ClubPlayersDocumentTest {

    private final ObjectMapper mapper = JacksonMapperSingleton.get();

    @Test
    public void shouldSerializeToJsonCorrectly() throws JsonProcessingException  {
        final String expectedJson = FileContentLoader.load("/playersDocument.json");
        final ClubPlayersDocument document = FakePlayerFactory.buildClubPlayersDocument();

        final String json = mapper.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    public void shouldSerializeToJsonCorrectlyWhenPartOfMultiplePages() throws JsonProcessingException  {
        final String expectedJson = FileContentLoader.load("/playersDocumentWithMultiplePages.json");
        final ClubPlayersDocument document = FakePlayerFactory.buildClubPlayersDocumentWithMultiplePages();

        final String json = mapper.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    public void shouldSerializeToJsonCorrectlyWhenNoDataPresent() throws JsonProcessingException  {
        final String expectedJson = FileContentLoader.load("/playersDocumentWithNoData.json");
        final ClubPlayersDocument document = FakePlayerFactory.buildClubPlayersDocumentWithNoData();

        final String json = mapper.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    public void shouldDeserializeFromJsonCorrectly() throws IOException {
        final String json = FileContentLoader.load("/playersDocument.json");
        final ClubPlayersDocument expectedDocument = FakePlayerFactory.buildClubPlayersDocument();

        final ClubPlayersDocument document = mapper.readValue(json, ClubPlayersDocument.class);

        assertThat(document).isEqualToComparingFieldByFieldRecursively(expectedDocument);
    }

}