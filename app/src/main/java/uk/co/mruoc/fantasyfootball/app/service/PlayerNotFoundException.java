package uk.co.mruoc.fantasyfootball.app.service;

public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException(long id) {
        super("player with id " + id + " not found");
    }

}
