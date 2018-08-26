package uk.co.mruoc.fantasyfootball.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import uk.co.mruoc.fantasyfootball.ClubData;
import uk.co.mruoc.fantasyfootball.FakeClubData;
import uk.co.mruoc.fantasyfootball.FakeClubFactory;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ClubDocumentTest {

    private static final ClubData CLUB_DATA = new FakeClubData();

    private static final String JSON = FileContentLoader.load("/clubDocument.json");

    private final ObjectMapper mapper = new ObjectMapper();

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
        ClubDocument document = FakeClubFactory.buildClubDocument();

        String json = mapper.writeValueAsString(document);

        assertThat(json).isEqualToIgnoringWhitespace(JSON);
    }

    @Test
    public void shouldDeserializeFromJsonCorrectly() throws IOException {
        ClubDocument expectedDocument = FakeClubFactory.buildClubDocument();

        ClubDocument document = mapper.readValue(JSON, ClubDocument.class);

        assertThat(document).isEqualToComparingFieldByFieldRecursively(expectedDocument);
    }

    @Test
    public void shouldReturnId() {
        ClubDocument document = FakeClubFactory.buildClubDocument(CLUB_DATA);

        assertThat(document.getId()).isEqualTo(CLUB_DATA.getId());
    }

    @Test
    public void shouldReturnName() {
        ClubDocument document = FakeClubFactory.buildClubDocument();

        assertThat(document.getName()).isEqualTo(CLUB_DATA.getName());
    }

}