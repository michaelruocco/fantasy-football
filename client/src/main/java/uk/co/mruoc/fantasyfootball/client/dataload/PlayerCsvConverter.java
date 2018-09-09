package uk.co.mruoc.fantasyfootball.client.dataload;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument.PlayerDocumentBuilder;

public class PlayerCsvConverter implements CsvRecordConverter<PlayerDocument> {

    @Override
    public PlayerDocument convert(CSVRecord record) {
        return new PlayerDocumentBuilder()
                .setId(Long.parseLong(record.get(0)))
                .setClubId(extractClubId(record))
                .setFirstName(record.get(2))
                .setLastName(record.get(3))
                .setPosition(record.get(4))
                .setValue(Integer.parseInt(record.get(5)))
                .build();
    }

    private Long extractClubId(CSVRecord record) {
        String value = record.get(1);
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return Long.parseLong(value);
    }

}
