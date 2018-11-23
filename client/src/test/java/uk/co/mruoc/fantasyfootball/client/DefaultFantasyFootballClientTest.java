package uk.co.mruoc.fantasyfootball.client;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.fantasyfootball.api.example.ExamplePlayerDocumentFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class DefaultFantasyFootballClientTest {

    private final ClubClient clubClient = mock(ClubClient.class);
    private final PlayerClient playerClient = mock(PlayerClient.class);

    private final FantasyFootballClient client = new DefaultFantasyFootballClient(clubClient, playerClient);

    @Test
    public void shouldCreateClub() {
        ClubDocument document = new ClubDocument();
        given(clubClient.create(document)).willReturn(document);

        ClubDocument resultDocument = client.create(document);

        assertThat(resultDocument).isEqualTo(document);
    }

    @Test
    public void shouldCreatePlayer() {
        PlayerDocument document = ExamplePlayerDocumentFactory.buildPlayerDocument1();
        given(playerClient.create(document)).willReturn(document);

        PlayerDocument resultDocument = client.create(document);

        assertThat(resultDocument).isEqualTo(document);
    }

}
