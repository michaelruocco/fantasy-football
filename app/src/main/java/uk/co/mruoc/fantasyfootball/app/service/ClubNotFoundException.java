package uk.co.mruoc.fantasyfootball.app.service;

import uk.co.mruoc.fantasyfootball.api.ClubNotFoundErrorData;
import uk.co.mruoc.fantasyfootball.api.ErrorData;
import uk.co.mruoc.fantasyfootball.api.JsonApiException;

public class ClubNotFoundException extends RuntimeException implements JsonApiException {

    private final long id;

    public ClubNotFoundException(long id) {
        super(String.format("club with id %s not found", id));
        this.id = id;
    }

    @Override
    public ErrorData getErrorData() {
        return new ClubNotFoundErrorData(id, getMessage());
    }

}
