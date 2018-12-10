package uk.co.mruoc.fantasyfootball.app.service;

public class UserNotFoundException extends AbstractNotFoundException {

    public UserNotFoundException(Object email) {
        super(String.format("user with email %s not found", email), email);
    }

}
