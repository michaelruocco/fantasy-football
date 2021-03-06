package uk.co.mruoc.fantasyfootball.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.file.ContentLoader;
import uk.co.mruoc.http.client.test.DefaultFakeHttpClient;
import uk.co.mruoc.http.client.test.FakeHttpClient;

import java.io.IOException;
import java.io.UncheckedIOException;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultPlayerClientTest {

    private static final String BASE_URL = "http://localhost:8080";

    private static final String JSON = ContentLoader.loadContentFromClasspath("/playerDocument.json");
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final PlayerDocument DOCUMENT = loadDocumentFromJson();

    private final FakeHttpClient httpClient = new DefaultFakeHttpClient();
    private final PlayerClient client = new DefaultPlayerClient(BASE_URL, httpClient);

    @Test
    public void shouldPostToCorrectUrlOnCreate() {
        final String expectedUrl = BASE_URL + "/players";
        httpClient.cannedResponse(201, JSON);

        client.create(DOCUMENT);

        assertThat(httpClient.lastRequestUri()).isEqualTo(expectedUrl);
    }

    @Test
    public void shouldConvertPlayerToJsonOnCreate() {
        httpClient.cannedResponse(201, JSON);

        client.create(DOCUMENT);

        assertThat(httpClient.lastRequestBody()).isEqualToIgnoringWhitespace(JSON);
    }

    @Test
    public void shouldReturnCreatedDocument() {
        httpClient.cannedResponse(201, JSON);

        final PlayerDocument createdDocument = client.create(DOCUMENT);

        assertThat(createdDocument).isEqualToComparingFieldByFieldRecursively(DOCUMENT);
    }

    private static PlayerDocument loadDocumentFromJson() {
        try {
            return MAPPER.readValue(JSON, PlayerDocument.class);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
