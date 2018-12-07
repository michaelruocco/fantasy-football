package uk.co.mruoc.fantasyfootball.app.service;

public class PlayerAlreadyExistsException extends AbstractAlreadyExistsException {

    public PlayerAlreadyExistsException(long id) {
        super(String.format("player with id %s already exists", id), id);
    }

}
