package uk.co.mruoc.fantasyfootball.app.service;

public class ClubAlreadyExistsException extends RuntimeException {

    public ClubAlreadyExistsException(long id) {
        super("club with id " + id + " already exists");
    }

}
