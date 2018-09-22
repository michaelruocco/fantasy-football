package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.example.ExamplePlayerDocumentFactory;
import uk.co.mruoc.fantasyfootball.api.example.PlayerData;
import uk.co.mruoc.fantasyfootball.api.example.PlayerData1;
import uk.co.mruoc.file.ContentLoader;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerDocumentTest {

    private static final PlayerData PLAYER_DATA1 = new PlayerData1();

    private final ObjectMapper mapper = JacksonMapperSingleton.get();

    @Test
    public void shouldReturnTrueIfHasId() {
        final PlayerDocument document = ExamplePlayerDocumentFactory.buildPlayerDocument1();

        assertThat(document.hasId()).isTrue();
    }

    @Test
    public void shouldReturnFalseIfDoesNotHaveId() {
        final PlayerDocument document = ExamplePlayerDocumentFactory.buildEmptyPlayerDocument();

        assertThat(document.hasId()).isFalse();
    }

    @Test
    public void shouldSerializeToJsonCorrectly() throws JsonProcessingException  {
        final String expectedJson = ContentLoader.loadContentFromClasspath("/playerDocument.json");
        final PlayerDocument document = ExamplePlayerDocumentFactory.buildPlayerDocument1();

        final String json = mapper.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    public void shouldDeserializeFromJsonCorrectly() throws IOException {
        final PlayerDocument expectedDocument = ExamplePlayerDocumentFactory.buildPlayerDocument1();
        final String json = ContentLoader.loadContentFromClasspath("/playerDocument.json");

        final PlayerDocument document = mapper.readValue(json, PlayerDocument.class);

        assertThat(document).isEqualToComparingFieldByFieldRecursively(expectedDocument);
    }

    @Test
    public void shouldSerializePlayerWithoutClubToJsonCorrectly() throws JsonProcessingException {
        final String expectedJson = ContentLoader.loadContentFromClasspath("/playerDocumentWithoutClub.json");
        final PlayerDocument document = ExamplePlayerDocumentFactory.buildPlayerDocumentWithoutClub();

        final String json = mapper.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    public void shouldReturnId() {
        final PlayerDocument document = ExamplePlayerDocumentFactory.buildPlayerDocument(PLAYER_DATA1);

        assertThat(document.getId()).isEqualTo(PLAYER_DATA1.getId());
    }

    @Test
    public void shouldReturnFirstName() {
        final PlayerDocument document = ExamplePlayerDocumentFactory.buildPlayerDocument(PLAYER_DATA1);

        assertThat(document.getFirstName()).isEqualTo(PLAYER_DATA1.getFirstName());
    }

    @Test
    public void shouldReturnLastName() {
        final PlayerDocument document = ExamplePlayerDocumentFactory.buildPlayerDocument(PLAYER_DATA1);

        assertThat(document.getLastName()).isEqualTo(PLAYER_DATA1.getLastName());
    }

    @Test
    public void shouldReturnPosition() {
        final PlayerDocument document = ExamplePlayerDocumentFactory.buildPlayerDocument(PLAYER_DATA1);

        assertThat(document.getPosition()).isEqualTo(PLAYER_DATA1.getPosition());
    }

    @Test
    public void shouldReturnValue() {
        final PlayerDocument document = ExamplePlayerDocumentFactory.buildPlayerDocument(PLAYER_DATA1);

        assertThat(document.getValue()).isEqualTo(PLAYER_DATA1.getValue());
    }

    @Test
    public void shouldReturnClubId() {
        final PlayerDocument document = ExamplePlayerDocumentFactory.buildPlayerDocument(PLAYER_DATA1);

        assertThat(document.getClubId()).isEqualTo(Optional.of(PLAYER_DATA1.getClubId()));
    }

    @Test
    public void shouldReturnEmptyOptionalIfClubIdNotSet() {
        final PlayerDocument document = ExamplePlayerDocumentFactory.buildPlayerDocumentWithoutClub();

        assertThat(document.getClubId()).isEqualTo(Optional.empty());
    }

    @Test
    public void shouldReturnSelfLink() {
        final PlayerDocument document = ExamplePlayerDocumentFactory.buildPlayerDocument(PLAYER_DATA1);

        assertThat(document.getSelfLink()).isEqualTo("/players/" + PLAYER_DATA1.getId());
    }

}