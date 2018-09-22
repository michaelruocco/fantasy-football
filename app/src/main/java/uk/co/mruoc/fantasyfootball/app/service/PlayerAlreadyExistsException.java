package uk.co.mruoc.fantasyfootball.app.service;

public class PlayerAlreadyExistsException extends RuntimeException {

    public PlayerAlreadyExistsException(long id) {
        super("player with id " + id + " already exists");
    }

}
