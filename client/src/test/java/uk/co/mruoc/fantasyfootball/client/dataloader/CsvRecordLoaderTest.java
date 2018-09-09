package uk.co.mruoc.fantasyfootball.client.dataloader;

import org.apache.commons.csv.CSVRecord;
import org.junit.Test;
import uk.co.mruoc.fantasyfootball.client.dataload.CsvRecordLoader;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static uk.co.mruoc.fantasyfootball.client.dataloader.ClasspathFilePathLoader.loadAbsolutePath;

public class CsvRecordLoaderTest {

    private final CsvRecordLoader loader = new CsvRecordLoader();

    @Test
    public void shouldLoadCsvRecords() {
        String path = loadAbsolutePath("/test-clubs.csv");

        List<CSVRecord> records  = loader.load(path);

        assertThat(records).hasSize(2);
    }

}
