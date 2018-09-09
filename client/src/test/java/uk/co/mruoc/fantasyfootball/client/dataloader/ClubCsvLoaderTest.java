package uk.co.mruoc.fantasyfootball.client.dataloader;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.client.dataload.ClubCsvLoader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ClubCsvLoaderTest {

    private final ClubCsvLoader loader = new ClubCsvLoader();

    @Test
    public void shouldLoadAllClubDataFromCsvIntoDocuments() {
        List<ClubDocument> documents = loader.load("data/clubs.csv");

        assertThat(documents).hasSize(2);
    }

    @Test
    public void shouldParseDataLineCorrectly() {
        List<ClubDocument> documents = loader.load("data/clubs.csv");

        ClubDocument document = documents.get(0);
        assertThat(document.getId()).isEqualTo(1L);
        assertThat(document.getName()).isEqualTo("Leeds United");
    }

}
