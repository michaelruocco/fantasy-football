package uk.co.mruoc.fantasyfootball.client.dataload;

import org.apache.commons.csv.CSVRecord;

import java.util.List;
import java.util.stream.Collectors;

public interface CsvRecordConverter<T> {

    default List<T> convert(List<CSVRecord> records) {
        return records.stream().map(this::convert).collect(Collectors.toList());
    }

    public abstract T convert(CSVRecord record);

}
