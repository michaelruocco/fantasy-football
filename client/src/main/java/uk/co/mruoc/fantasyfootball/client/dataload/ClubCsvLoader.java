package uk.co.mruoc.fantasyfootball.client.dataload;

import uk.co.mruoc.fantasyfootball.api.ClubDocument;

public class ClubCsvLoader extends CsvLoader<ClubDocument> {

    public ClubCsvLoader() {
        super(new ClubCsvConverter(), new CsvRecordLoader());
    }

}
