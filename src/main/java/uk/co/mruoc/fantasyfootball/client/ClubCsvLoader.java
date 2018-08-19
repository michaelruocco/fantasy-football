package uk.co.mruoc.fantasyfootball.client;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.ClubDocument.ClubDocumentBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

public class ClubCsvLoader {

    public List<ClubDocument> load(String path) {
        try {
            List<ClubDocument> clubs = new ArrayList<>();
            Reader in = new FileReader(path);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            for (CSVRecord record : records) {
                clubs.add(toDocument(record));
            }
            return clubs;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private ClubDocument toDocument(CSVRecord record) {
        return new ClubDocumentBuilder()
                .setId(Long.parseLong(record.get(0)))
                .setName(record.get(1))
                .build();
    }

}
