package uk.co.mruoc.fantasyfootball.app.service;

import uk.co.mruoc.fantasyfootball.api.ErrorData;
import uk.co.mruoc.fantasyfootball.api.JsonApiException;
import uk.co.mruoc.fantasyfootball.api.UserIdNotFoundErrorData;

public class UserIdNotFoundException extends RuntimeException implements JsonApiException {

    private final long id;

    public UserIdNotFoundException(final long id) {
        super(String.format("user with id %s not found", id));
        this.id = id;
    }

    @Override
    public ErrorData getErrorData() {
        return new UserIdNotFoundErrorData(id, getMessage());
    }

}
