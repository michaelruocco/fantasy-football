package uk.co.mruoc.fantasyfootball.client;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument.PlayerDocumentBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

public class PlayerCsvLoader {

    public List<PlayerDocument> load(String path) {
        try {
            List<PlayerDocument> players = new ArrayList<>();
            Reader in = new FileReader(path);
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            for (CSVRecord record : records) {
                players.add(toDocument(record));
            }
            return players;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private PlayerDocument toDocument(CSVRecord record) {
        return new PlayerDocumentBuilder()
                .setId(Long.parseLong(record.get(0)))
                .setClubId(Long.parseLong(record.get(1)))
                .setFirstName(record.get(2))
                .setLastName(record.get(3))
                .setPosition(record.get(4))
                .setValue(Integer.parseInt(record.get(5)))
                .build();
    }

}
