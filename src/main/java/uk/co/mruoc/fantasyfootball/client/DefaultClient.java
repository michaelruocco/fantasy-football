package uk.co.mruoc.fantasyfootball.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;
import uk.co.mruoc.http.client.SimpleHttpClient;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public class DefaultClient implements Client {

    private final JsonConverter converter = new JsonConverter(buildMapper());
    private final String baseUrl;
    private final HttpClient httpClient;

    public DefaultClient(String baseUrl) {
        this(baseUrl, new SimpleHttpClient());
    }

    public DefaultClient(String baseUrl, HttpClient httpClient) {
        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
    }

    @Override
    public ClubDocument create(ClubDocument clubDocument) {
        final String json = converter.toJson(clubDocument);
        final Response response = httpClient.post(baseUrl + "/clubs", json);
        return converter.fromJson(response.getBody(), ClubDocument.class);
    }

    @Override
    public PlayerDocument create(PlayerDocument playerDocument) {
        final String json = converter.toJson(playerDocument);
        final Response response = httpClient.post(baseUrl + "/players", json);
        return converter.fromJson(response.getBody(), PlayerDocument.class);
    }

    private static ObjectMapper buildMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(NON_NULL);
        return mapper;
    }

}
