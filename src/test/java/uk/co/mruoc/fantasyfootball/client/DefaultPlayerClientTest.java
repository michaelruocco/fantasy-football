package uk.co.mruoc.fantasyfootball.client;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.FakePlayerFactory;
import uk.co.mruoc.fantasyfootball.api.FileContentLoader;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.http.client.test.DefaultFakeHttpClient;
import uk.co.mruoc.http.client.test.FakeHttpClient;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultPlayerClientTest {

    private static final String BASE_URL = "http://localhost:8080";
    private static final String JSON = FileContentLoader.load("/playerDocument.json");
    private final FakeHttpClient httpClient = new DefaultFakeHttpClient();

    private final PlayerClient client = new DefaultPlayerClient(BASE_URL, httpClient, JsonConverterFactory.build());

    @Test
    public void shouldPostToCorrectUrlOnCreate() {
        final String expectedUrl = BASE_URL + "/players";
        final PlayerDocument document = FakePlayerFactory.buildPlayerDocument1();
        httpClient.cannedResponse(201, JSON);

        client.create(document);

        assertThat(httpClient.lastRequestUri()).isEqualTo(expectedUrl);
    }

    @Test
    public void shouldConvertPlayerToJsonOnCreate() {
        final PlayerDocument document = FakePlayerFactory.buildPlayerDocument1();
        httpClient.cannedResponse(201, JSON);

        client.create(document);

        assertThat(httpClient.lastRequestBody()).isEqualToIgnoringWhitespace(JSON);
    }

    @Test
    public void shouldReturnCreatedDocument() {
        final PlayerDocument document = FakePlayerFactory.buildPlayerDocument1();
        httpClient.cannedResponse(201, JSON);

        final PlayerDocument createdDocument = client.create(document);

        assertThat(createdDocument).isEqualToComparingFieldByFieldRecursively(document);
    }

}
