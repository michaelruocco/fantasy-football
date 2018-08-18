package uk.co.mruoc.fantasyfootball.service;

public class PlayerNotFoundException extends RuntimeException {

    public PlayerNotFoundException(long id) {
        super("player with id " + id + " not found");
    }

}
