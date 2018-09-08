package uk.co.mruoc.fantasyfootball.client;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.fantasyfootball.api.example.ExamplePlayerDocumentFactory;
import uk.co.mruoc.file.ContentLoader;
import uk.co.mruoc.http.client.test.DefaultFakeHttpClient;
import uk.co.mruoc.http.client.test.FakeHttpClient;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultPlayerClientTest {

    private static final String BASE_URL = "http://localhost:8080";

    private static final String JSON = ContentLoader.loadContentFromClasspath("/playerDocument.json");
    private static final PlayerDocument DOCUMENT = ExamplePlayerDocumentFactory.buildPlayerDocument1();

    private final FakeHttpClient httpClient = new DefaultFakeHttpClient();
    private final PlayerClient client = new DefaultPlayerClient(BASE_URL, httpClient, JsonConverterFactory.build());

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

}
