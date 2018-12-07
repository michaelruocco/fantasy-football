package uk.co.mruoc.fantasyfootball.app.service;

public class PlayerNotFoundException extends AbstractNotFoundException {

    public PlayerNotFoundException(long id) {
        super(String.format("player with id %s not found", id), id);
    }

}
