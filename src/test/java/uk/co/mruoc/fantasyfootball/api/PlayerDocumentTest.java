package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import uk.co.mruoc.fantasyfootball.FakePlayerFactory;
import uk.co.mruoc.fantasyfootball.PlayerData;
import uk.co.mruoc.fantasyfootball.PlayerData1;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerDocumentTest {

    private static final PlayerData PLAYER_DATA1 = new PlayerData1();

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
        String expectedJson = FileContentLoader.load("/playerDocument.json");
        PlayerDocument document = FakePlayerFactory.buildPlayerDocument1();

        String json = mapper.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    public void shouldDeserializeFromJsonCorrectly() throws IOException {
        PlayerDocument expectedDocument = FakePlayerFactory.buildPlayerDocument1();
        String json = FileContentLoader.load("/playerDocument.json");

        PlayerDocument document = mapper.readValue(json, PlayerDocument.class);

        assertThat(document).isEqualToComparingFieldByFieldRecursively(expectedDocument);
    }

    @Test
    public void shouldSerializePlayerWithoutClubToJsonCorrectly() throws JsonProcessingException {
        String expectedJson = FileContentLoader.load("/playerDocumentWithoutClub.json");
        PlayerDocument document = FakePlayerFactory.buildPlayerDocumentWithoutClub();

        String json = mapper.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    public void shouldReturnId() {
        PlayerDocument document = FakePlayerFactory.buildPlayerDocument(PLAYER_DATA1);

        assertThat(document.getId()).isEqualTo(PLAYER_DATA1.getId());
    }

    @Test
    public void shouldReturnFirstName() {
        PlayerDocument document = FakePlayerFactory.buildPlayerDocument(PLAYER_DATA1);

        assertThat(document.getFirstName()).isEqualTo(PLAYER_DATA1.getFirstName());
    }

    @Test
    public void shouldReturnLastName() {
        PlayerDocument document = FakePlayerFactory.buildPlayerDocument(PLAYER_DATA1);

        assertThat(document.getLastName()).isEqualTo(PLAYER_DATA1.getLastName());
    }

    @Test
    public void shouldReturnPosition() {
        PlayerDocument document = FakePlayerFactory.buildPlayerDocument(PLAYER_DATA1);

        assertThat(document.getPosition()).isEqualTo(PLAYER_DATA1.getPositionName());
    }

    @Test
    public void shouldReturnValue() {
        PlayerDocument document = FakePlayerFactory.buildPlayerDocument(PLAYER_DATA1);

        assertThat(document.getValue()).isEqualTo(PLAYER_DATA1.getValue());
    }

    @Test
    public void shouldReturnClubId() {
        PlayerDocument document = FakePlayerFactory.buildPlayerDocument(PLAYER_DATA1);

        assertThat(document.getClubId()).isEqualTo(Optional.of(PLAYER_DATA1.getClubId()));
    }

    @Test
    public void shouldReturnEmptyOptionalIfClubIdNotSet() {
        PlayerDocument document = FakePlayerFactory.buildPlayerDocumentWithoutClub();

        assertThat(document.getClubId()).isEqualTo(Optional.empty());
    }

}