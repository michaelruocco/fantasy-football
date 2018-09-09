package uk.co.mruoc.fantasyfootball.client.dataloader;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.client.dataload.ClubCsvLoader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.fantasyfootball.client.dataloader.ClasspathFilePathLoader.loadAbsolutePath;

public class ClubCsvLoaderTest {

    private final ClubCsvLoader loader = new ClubCsvLoader();

    @Test
    public void shouldLoadAllClubDataFromCsvIntoDocuments() {
        String path = loadAbsolutePath("/test-clubs.csv");

        List<ClubDocument> documents = loader.load(path);

        assertThat(documents).hasSize(2);
    }

    @Test
    public void shouldParseDataLineCorrectly() {
        String path = loadAbsolutePath("/test-clubs.csv");

        List<ClubDocument> documents = loader.load(path);

        ClubDocument document = documents.get(0);
        assertThat(document.getId()).isEqualTo(1L);
        assertThat(document.getName()).isEqualTo("Test Club");
    }

}
