package uk.co.mruoc.fantasyfootball.client;

import uk.co.mruoc.fantasyfootball.api.JsonConverter;
import uk.co.mruoc.fantasyfootball.api.JsonConverterSingleton;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;

public class DefaultPlayerClient implements PlayerClient {

    private final JsonConverter converter;
    private final String baseUrl;
    private final HttpClient httpClient;

    public DefaultPlayerClient(String baseUrl, HttpClient httpClient) {
        this(baseUrl, httpClient, JsonConverterSingleton.get());
    }

    public DefaultPlayerClient(String baseUrl, HttpClient httpClient, JsonConverter converter) {
        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
        this.converter = converter;
    }

    @Override
    public PlayerDocument create(PlayerDocument playerDocument) {
        final String json = converter.toJson(playerDocument);
        final Response response = httpClient.post(baseUrl + "/players", json);
        return converter.fromJson(response.getBody(), PlayerDocument.class);
    }

}
