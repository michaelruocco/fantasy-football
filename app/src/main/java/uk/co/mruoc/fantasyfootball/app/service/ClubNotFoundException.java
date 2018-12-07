package uk.co.mruoc.fantasyfootball.app.service;

public class ClubNotFoundException extends AbstractNotFoundException {

    public ClubNotFoundException(long id) {
        super(String.format("club with id %s not found", id), id);
    }

}
