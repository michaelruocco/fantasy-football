package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import uk.co.mruoc.file.ContentLoader;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.fantasyfootball.api.PlayerDocument.*;

public class PlayerDocumentTest {

    private static final String JSON_PATH = "/playerDocument.json";
    private static final String JSON = ContentLoader.loadContentFromClasspath(JSON_PATH);
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static final long ID = 1133;
    private static final String FIRST_NAME = "Joe";
    private static final String LAST_NAME = "Bloggs";
    private static final String POSITION = "DEFENDER";
    private static final Integer VALUE = 50000000;
    private static final String SELF_LINK = String.format("/players/%s", ID);
    private static final long CLUB_ID = 1234;
    private static final String CLUB_LINK = String.format("/clubs/%s", CLUB_ID);

    @Test
    public void shouldSerializeToJsonCorrectly() throws JsonProcessingException {
        final PlayerDocument document = buildPlayerDocument();

        final String json = MAPPER.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(JSON);
    }

    @Test
    public void shouldDeserializeFromJsonCorrectly() throws IOException {
        final PlayerDocument expectedDocument = buildPlayerDocument();

        final PlayerDocument document = MAPPER.readValue(JSON, PlayerDocument.class);

        assertThat(document).isEqualToComparingFieldByFieldRecursively(expectedDocument);
    }

    @Test
    public void shouldReturnTrueIfHasId() {
        final PlayerDocument document = buildPlayerDocument();

        assertThat(document.hasId()).isTrue();
    }

    @Test
    public void shouldReturnFalseIfDoesNotHaveId() {
        final PlayerDocument document = new PlayerDocument();

        assertThat(document.hasId()).isFalse();
    }

    @Test
    public void shouldReturnId() {
        final PlayerDocument document = buildPlayerDocument();

        assertThat(document.getId()).isEqualTo(ID);
    }

    @Test
    public void shouldReturnFirstName() {
        final PlayerDocument document = buildPlayerDocument();

        assertThat(document.getFirstName()).isEqualTo(FIRST_NAME);
    }

    @Test
    public void shouldReturnLastName() {
        final PlayerDocument document = buildPlayerDocument();

        assertThat(document.getLastName()).isEqualTo(LAST_NAME);
    }

    @Test
    public void shouldReturnPosition() {
        final PlayerDocument document = buildPlayerDocument();

        assertThat(document.getPosition()).isEqualTo(POSITION);
    }

    @Test
    public void shouldReturnValue() {
        final PlayerDocument document = buildPlayerDocument();

        assertThat(document.getValue()).isEqualTo(VALUE);
    }

    @Test
    public void shouldReturnClubId() {
        final PlayerDocument document = buildPlayerDocument();

        assertThat(document.getClubId()).isEqualTo(Optional.of(CLUB_ID));
    }

    @Test
    public void shouldReturnEmptyOptionalIfClubIdNotSet() {
        final PlayerDocument document = new PlayerDocument();

        assertThat(document.getClubId()).isEqualTo(Optional.empty());
    }

    @Test
    public void shouldReturnSelfLink() {
        final PlayerDocument document = buildPlayerDocument();

        assertThat(document.getSelfLink()).isEqualTo(SELF_LINK);
    }

    private static PlayerDocument buildPlayerDocument() {
        return new PlayerDocumentBuilder()
                .setId(ID)
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setPosition(POSITION)
                .setValue(VALUE)
                .setSelfLink(SELF_LINK)
                .setClubId(CLUB_ID)
                .setClubLink(CLUB_LINK)
                .build();
    }

}