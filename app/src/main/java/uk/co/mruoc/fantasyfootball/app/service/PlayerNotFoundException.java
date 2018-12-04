package uk.co.mruoc.fantasyfootball.app.service;

public class PlayerNotFoundException extends RuntimeException {

    private final long id;

    public PlayerNotFoundException(long id) {
        super("player with id " + id + " not found");
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
