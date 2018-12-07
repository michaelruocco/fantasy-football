package uk.co.mruoc.fantasyfootball.client.dataloader;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.fantasyfootball.client.dataload.PlayerCsvLoader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.fantasyfootball.client.dataloader.ClasspathFilePathLoader.loadAbsolutePath;

public class PlayerCsvLoaderTest {

    private final PlayerCsvLoader loader = new PlayerCsvLoader();

    @Test
    public void shouldLoadAllPlayerDataFromCsvIntoDocuments() {
        String path = loadAbsolutePath("/test-players.csv");

        List<PlayerDocument> documents = loader.load(path);

        assertThat(documents).hasSize(2);
    }

    @Test
    public void shouldParseDataLineCorrectly() {
        String path = loadAbsolutePath("/test-players.csv");

        List<PlayerDocument> documents = loader.load(path);

        PlayerDocument document = documents.get(0);
        assertThat(document.getId()).isEqualTo(1L);
        assertThat(document.getFirstName()).isEqualTo("Player1");
        assertThat(document.getLastName()).isEqualTo("WithClub");
        assertThat(document.getPosition()).isEqualTo("GOALKEEPER");
        assertThat(document.getValue()).isEqualTo(1000000);
        assertThat(document.hasClub()).isTrue();
        assertThat(document.getClubId()).isEqualTo(1L);
    }

}
