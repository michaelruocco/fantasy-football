package uk.co.mruoc.fantasyfootball.app.service;

public class AbstractAlreadyExistsException extends RuntimeException implements IdAwareException {

    private final long id;

    public AbstractAlreadyExistsException(final String message, final long id) {
        super(message);
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
