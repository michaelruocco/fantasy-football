package uk.co.mruoc.fantasyfootball.client;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.FakeClubFactory;
import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.FileContentLoader;
import uk.co.mruoc.http.client.test.DefaultFakeHttpClient;
import uk.co.mruoc.http.client.test.FakeHttpClient;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultClubClientTest {

    private static final String BASE_URL = "http://localhost:8080";
    private static final String JSON = FileContentLoader.load("/clubDocument.json");
    private final FakeHttpClient httpClient = new DefaultFakeHttpClient();

    private final ClubClient client = new DefaultClubClient(BASE_URL, httpClient, JsonConverterFactory.build());

    @Test
    public void shouldPostToCorrectUrlOnCreate() {
        final String expectedUrl = BASE_URL + "/clubs";
        final ClubDocument document = FakeClubFactory.buildClubDocument1();
        httpClient.cannedResponse(201, JSON);

        client.create(document);

        assertThat(httpClient.lastRequestUri()).isEqualTo(expectedUrl);
    }

    @Test
    public void shouldConvertClubToJsonOnCreate() {
        final ClubDocument document = FakeClubFactory.buildClubDocument1();
        httpClient.cannedResponse(201, JSON);

        client.create(document);

        assertThat(httpClient.lastRequestBody()).isEqualToIgnoringWhitespace(JSON);
    }

    @Test
    public void shouldReturnCreatedDocument() {
        final ClubDocument document = FakeClubFactory.buildClubDocument1();
        httpClient.cannedResponse(201, JSON);

        final ClubDocument createdDocument = client.create(document);

        assertThat(createdDocument).isEqualToComparingFieldByFieldRecursively(document);
    }

}
