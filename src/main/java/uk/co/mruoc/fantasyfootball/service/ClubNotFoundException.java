package uk.co.mruoc.fantasyfootball.service;

public class ClubNotFoundException extends RuntimeException {

    public ClubNotFoundException(long id) {
        super("club with id " + id + " not found");
    }

}
