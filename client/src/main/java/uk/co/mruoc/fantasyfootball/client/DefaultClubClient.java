package uk.co.mruoc.fantasyfootball.client;

import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.JsonConverter;
import uk.co.mruoc.fantasyfootball.api.JsonConverterSingleton;
import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.Response;

public class DefaultClubClient implements ClubClient {

    private final JsonConverter converter;
    private final String baseUrl;
    private final HttpClient httpClient;

    public DefaultClubClient(String baseUrl, HttpClient httpClient) {
        this(baseUrl, httpClient, JsonConverterSingleton.get());
    }

    public DefaultClubClient(String baseUrl, HttpClient httpClient, JsonConverter converter) {
        this.baseUrl = baseUrl;
        this.httpClient = httpClient;
        this.converter = converter;
    }

    @Override
    public ClubDocument create(ClubDocument clubDocument) {
        final String json = converter.toJson(clubDocument);
        final Response response = httpClient.post(baseUrl + "/clubs", json);
        return converter.fromJson(response.getBody(), ClubDocument.class);
    }

}
