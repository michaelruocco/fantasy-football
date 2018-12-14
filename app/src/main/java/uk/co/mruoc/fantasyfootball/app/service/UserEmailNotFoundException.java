package uk.co.mruoc.fantasyfootball.app.service;

import uk.co.mruoc.fantasyfootball.api.ErrorData;
import uk.co.mruoc.fantasyfootball.api.JsonApiException;
import uk.co.mruoc.fantasyfootball.api.UserEmailNotFoundErrorData;

public class UserEmailNotFoundException extends RuntimeException implements JsonApiException {

    private final String email;

    public UserEmailNotFoundException(String email) {
        super(String.format("user with email %s not found", email));
        this.email = email;
    }

    @Override
    public ErrorData getErrorData() {
        return new UserEmailNotFoundErrorData(email, getMessage());
    }

}
