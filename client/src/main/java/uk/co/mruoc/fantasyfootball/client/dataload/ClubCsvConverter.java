package uk.co.mruoc.fantasyfootball.client.dataload;

import org.apache.commons.csv.CSVRecord;
import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.ClubDocument.ClubDocumentBuilder;

public class ClubCsvConverter implements CsvRecordConverter<ClubDocument> {

    @Override
    public ClubDocument convert(CSVRecord record) {
        return new ClubDocumentBuilder()
                .setId(Long.parseLong(record.get(0)))
                .setName(record.get(1))
                .build();
    }

}
