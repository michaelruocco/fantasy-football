package uk.co.mruoc.fantasyfootball.client.dataload;

import org.apache.commons.csv.CSVRecord;

import java.util.List;

public class CsvLoader<T> {

    private final CsvRecordConverter<T> converter;
    private final CsvRecordLoader loader;

    public CsvLoader(CsvRecordConverter<T> converter, CsvRecordLoader loader) {
        this.converter = converter;
        this.loader = loader;
    }

    public List<T> load(String path) {
        final List<CSVRecord> records = loader.load(path);
        return converter.convert(records);
    }

}
