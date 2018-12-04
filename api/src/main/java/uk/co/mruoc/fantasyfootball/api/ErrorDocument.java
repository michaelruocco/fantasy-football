package uk.co.mruoc.fantasyfootball.api;

import java.util.List;

import static java.util.Arrays.asList;

public class ErrorDocument {

    private final List<ErrorData> errors;

    public ErrorDocument(ErrorData... error) {
        this(asList(error));
    }

    public ErrorDocument(List<ErrorData> errors) {
        this.errors = errors;
    }

    public List<ErrorData> getErrors() {
        return errors;
    }

}
