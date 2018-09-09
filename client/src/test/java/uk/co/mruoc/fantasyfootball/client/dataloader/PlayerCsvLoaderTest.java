package uk.co.mruoc.fantasyfootball.client.dataloader;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.fantasyfootball.client.dataload.PlayerCsvLoader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PlayerCsvLoaderTest {

    private final PlayerCsvLoader loader = new PlayerCsvLoader();

    @Test
    public void shouldLoadAllPlayerDataFromCsvIntoDocuments() {
        List<PlayerDocument> documents = loader.load("data/players.csv");

        assertThat(documents).hasSize(36);
    }

    @Test
    public void shouldParseDataLineCorrectly() {
        List<PlayerDocument> documents = loader.load("data/players.csv");

        PlayerDocument document = documents.get(0);
        assertThat(document.getId()).isEqualTo(1L);
        assertThat(document.getFirstName()).isEqualTo("Bailey");
        assertThat(document.getLastName()).isEqualTo("Peacock-Farrell");
        assertThat(document.getPosition()).isEqualTo("GOALKEEPER");
        assertThat(document.getValue()).isEqualTo(1000000);
        assertThat(document.getClubId().isPresent()).isTrue();
        assertThat(document.getClubId().get()).isEqualTo(1L);
    }

}
