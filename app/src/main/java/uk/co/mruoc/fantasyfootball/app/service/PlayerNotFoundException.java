package uk.co.mruoc.fantasyfootball.app.service;

import uk.co.mruoc.fantasyfootball.api.ErrorData;
import uk.co.mruoc.fantasyfootball.api.JsonApiException;
import uk.co.mruoc.fantasyfootball.api.PlayerNotFoundErrorData;

public class PlayerNotFoundException extends RuntimeException implements JsonApiException {

    private final long id;

    public PlayerNotFoundException(long id) {
        super(String.format("player with id %s not found", id));
        this.id = id;
    }

    @Override
    public ErrorData getErrorData() {
        return new PlayerNotFoundErrorData(id, getMessage());
    }

}
