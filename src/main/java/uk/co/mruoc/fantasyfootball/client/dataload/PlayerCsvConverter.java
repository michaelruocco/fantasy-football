package uk.co.mruoc.fantasyfootball.client.dataload;

import org.apache.commons.csv.CSVRecord;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument.PlayerDocumentBuilder;

public class PlayerCsvConverter implements CsvRecordConverter<PlayerDocument> {

    @Override
    public PlayerDocument convert(CSVRecord record) {
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
