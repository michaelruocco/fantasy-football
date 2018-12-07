package uk.co.mruoc.fantasyfootball.app.service;

public class ClubAlreadyExistsException extends AbstractAlreadyExistsException {

    public ClubAlreadyExistsException(long id) {
        super(String.format("club with id %s already exists", id), id);
    }

}
