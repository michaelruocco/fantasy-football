package uk.co.mruoc.fantasyfootball.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import uk.co.mruoc.fantasyfootball.web.ClubDocument.ClubDocumentBuilder;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ClubDocumentTest {

    private static final String JSON = FileContentLoader.load("/clubDocument.json");

    private static final long ID = 1234;
    private static final String NAME = "Test Club";

    private final ObjectMapper mapper = new ObjectMapper();
    private final ClubDocumentBuilder builder = new ClubDocumentBuilder()
            .setId(ID)
            .setName(NAME);

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
        ClubDocument document = builder.build();

        String json = mapper.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(JSON);
    }

    @Test
    public void shouldDeserializeFromJsonCorrectly() throws IOException {
        ClubDocument expectedDocument = builder.build();

        ClubDocument document = mapper.readValue(JSON, ClubDocument.class);

        assertThat(document).isEqualToComparingFieldByFieldRecursively(expectedDocument);
    }

    @Test
    public void shouldReturnId() {
        ClubDocument document = builder.build();

        assertThat(document.getId()).isEqualTo(ID);
    }

    @Test
    public void shouldReturnName() {
        ClubDocument document = builder.build();

        assertThat(document.getName()).isEqualTo(NAME);
    }

}