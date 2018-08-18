package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument.PlayerDocumentBuilder;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerDocumentTest {

    private static final long ID = 9999;
    private static final String FIRST_NAME = "Michael";
    private static final String LAST_NAME = "Ruocco";
    private static final String POSITION = "STRIKER";
    private static final int VALUE = 10000000;
    private static final long CLUB_ID = 4321;

    private final ObjectMapper mapper = JacksonMapperSingleton.get();

    private final PlayerDocumentBuilder builder = new PlayerDocumentBuilder()
            .setId(ID)
            .setFirstName(FIRST_NAME)
            .setLastName(LAST_NAME)
            .setPosition(POSITION)
            .setValue(VALUE)
            .setClubId(CLUB_ID);

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
        PlayerDocument document = builder.build();

        String json = mapper.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    public void shouldDeserializeFromJsonCorrectly() throws IOException {
        PlayerDocument expectedDocument = builder.build();
        String json = FileContentLoader.load("/playerDocument.json");

        PlayerDocument document = mapper.readValue(json, PlayerDocument.class);

        assertThat(document).isEqualToComparingFieldByFieldRecursively(expectedDocument);
    }

    @Test
    public void shouldSerializeToJsonCorrectlyIfClubIdNotSet() throws JsonProcessingException {
        String expectedJson = FileContentLoader.load("/playerDocumentWithoutClub.json");
        PlayerDocument document = new PlayerDocumentBuilder()
                .setId(ID)
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setPosition(POSITION)
                .setValue(VALUE)
                .build();

        String json = mapper.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    public void shouldReturnId() {
        PlayerDocument document = builder.build();

        assertThat(document.getId()).isEqualTo(ID);
    }

    @Test
    public void shouldReturnFirstName() {
        PlayerDocument document = builder.build();

        assertThat(document.getFirstName()).isEqualTo(FIRST_NAME);
    }

    @Test
    public void shouldReturnLastName() {
        PlayerDocument document = builder.build();

        assertThat(document.getLastName()).isEqualTo(LAST_NAME);
    }

    @Test
    public void shouldReturnPosition() {
        PlayerDocument document = builder.build();

        assertThat(document.getPosition()).isEqualTo(POSITION);
    }

    @Test
    public void shouldReturnValue() {
        PlayerDocument document = builder.build();

        assertThat(document.getValue()).isEqualTo(VALUE);
    }

    @Test
    public void shouldReturnClubId() {
        PlayerDocument document = builder.build();

        assertThat(document.getClubId()).isEqualTo(Optional.of(CLUB_ID));
    }

    @Test
    public void shouldReturnEmptyOptionalIfClubIdNotSet() {
        PlayerDocument document = new PlayerDocumentBuilder().build();

        assertThat(document.getClubId()).isEqualTo(Optional.empty());
    }

}