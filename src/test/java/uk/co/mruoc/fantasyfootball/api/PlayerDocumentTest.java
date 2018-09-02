package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import uk.co.mruoc.fantasyfootball.FakePlayerFactory;
import uk.co.mruoc.fantasyfootball.PlayerData;
import uk.co.mruoc.fantasyfootball.PlayerData1;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerDocumentTest {

    private static final PlayerData PLAYER_DATA1 = new PlayerData1();

    private final ObjectMapper mapper = JacksonMapperSingleton.get();

    @Test
    public void shouldSerializeToJsonCorrectly() throws JsonProcessingException  {
        final String expectedJson = FileContentLoader.load("/playerDocument.json");
        final PlayerDocument document = FakePlayerFactory.buildPlayerDocument1();

        final String json = mapper.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    public void shouldDeserializeFromJsonCorrectly() throws IOException {
        final PlayerDocument expectedDocument = FakePlayerFactory.buildPlayerDocument1();
        final String json = FileContentLoader.load("/playerDocument.json");

        final PlayerDocument document = mapper.readValue(json, PlayerDocument.class);

        assertThat(document).isEqualToComparingFieldByFieldRecursively(expectedDocument);
    }

    @Test
    public void shouldSerializePlayerWithoutClubToJsonCorrectly() throws JsonProcessingException {
        final String expectedJson = FileContentLoader.load("/playerDocumentWithoutClub.json");
        final PlayerDocument document = FakePlayerFactory.buildPlayerDocumentWithoutClub();

        final String json = mapper.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    public void shouldReturnId() {
        final PlayerDocument document = FakePlayerFactory.buildPlayerDocument(PLAYER_DATA1);

        assertThat(document.getId()).isEqualTo(PLAYER_DATA1.getId());
    }

    @Test
    public void shouldReturnFirstName() {
        final PlayerDocument document = FakePlayerFactory.buildPlayerDocument(PLAYER_DATA1);

        assertThat(document.getFirstName()).isEqualTo(PLAYER_DATA1.getFirstName());
    }

    @Test
    public void shouldReturnLastName() {
        final PlayerDocument document = FakePlayerFactory.buildPlayerDocument(PLAYER_DATA1);

        assertThat(document.getLastName()).isEqualTo(PLAYER_DATA1.getLastName());
    }

    @Test
    public void shouldReturnPosition() {
        final PlayerDocument document = FakePlayerFactory.buildPlayerDocument(PLAYER_DATA1);

        assertThat(document.getPosition()).isEqualTo(PLAYER_DATA1.getPositionName());
    }

    @Test
    public void shouldReturnValue() {
        final PlayerDocument document = FakePlayerFactory.buildPlayerDocument(PLAYER_DATA1);

        assertThat(document.getValue()).isEqualTo(PLAYER_DATA1.getValue());
    }

    @Test
    public void shouldReturnClubId() {
        final PlayerDocument document = FakePlayerFactory.buildPlayerDocument(PLAYER_DATA1);

        assertThat(document.getClubId()).isEqualTo(Optional.of(PLAYER_DATA1.getClubId()));
    }

    @Test
    public void shouldReturnEmptyOptionalIfClubIdNotSet() {
        final PlayerDocument document = FakePlayerFactory.buildPlayerDocumentWithoutClub();

        assertThat(document.getClubId()).isEqualTo(Optional.empty());
    }

}