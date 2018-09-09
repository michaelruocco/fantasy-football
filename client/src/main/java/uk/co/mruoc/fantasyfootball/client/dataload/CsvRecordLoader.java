package uk.co.mruoc.fantasyfootball.client.dataload;

import com.google.common.collect.Lists;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.List;

public class CsvRecordLoader {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvRecordLoader.class);

    public List<CSVRecord> load(String path) {
        try {
            LOGGER.info("attempting to load csv records from path " + path);
            final Reader in = new FileReader(path);
            final Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(in);
            return Lists.newArrayList(records);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}

