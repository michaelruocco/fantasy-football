package uk.co.mruoc.fantasyfootball.client.dataload;

import uk.co.mruoc.fantasyfootball.api.PlayerDocument;

public class PlayerCsvLoader extends CsvLoader<PlayerDocument> {

    public PlayerCsvLoader() {
        super(new PlayerCsvConverter(), new CsvRecordLoader());
    }

}
