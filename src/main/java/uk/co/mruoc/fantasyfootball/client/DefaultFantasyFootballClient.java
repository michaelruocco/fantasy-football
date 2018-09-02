package uk.co.mruoc.fantasyfootball.client;

import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.http.client.HttpClient;
import uk.co.mruoc.http.client.SimpleHttpClient;

public class DefaultFantasyFootballClient implements FantasyFootballClient {

    private static final HttpClient HTTP_CLIENT = new SimpleHttpClient();
    private static final JsonConverter CONVERTER = JsonConverterFactory.build();

    private final ClubClient clubClient;
    private final PlayerClient playerClient;

    public DefaultFantasyFootballClient(String baseUrl) {
        this(buildClubClient(baseUrl), buildPlayerClient(baseUrl));
    }

    public DefaultFantasyFootballClient(ClubClient clubClient, PlayerClient playerClient) {
        this.clubClient = clubClient;
        this.playerClient = playerClient;
    }

    @Override
    public ClubDocument create(ClubDocument clubDocument) {
        return clubClient.create(clubDocument);
    }

    @Override
    public PlayerDocument create(PlayerDocument playerDocument) {
        return playerClient.create(playerDocument);
    }

    private static ClubClient buildClubClient(String baseUrl) {
        return new DefaultClubClient(baseUrl, HTTP_CLIENT, CONVERTER);
    }

    private static PlayerClient buildPlayerClient(String baseUrl) {
        return new DefaultPlayerClient(baseUrl, HTTP_CLIENT, CONVERTER);
    }

}
