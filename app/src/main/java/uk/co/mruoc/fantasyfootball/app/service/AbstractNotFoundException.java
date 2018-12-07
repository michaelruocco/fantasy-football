package uk.co.mruoc.fantasyfootball.app.service;

public class AbstractNotFoundException extends RuntimeException implements IdAwareException {

    private final long id;

    public AbstractNotFoundException(final String message, final long id) {
        super(message);
        this.id = id;
    }

    public long getId() {
        return id;
    }

}
