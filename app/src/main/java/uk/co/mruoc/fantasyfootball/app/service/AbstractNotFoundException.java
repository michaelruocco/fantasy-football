package uk.co.mruoc.fantasyfootball.app.service;

public class AbstractNotFoundException extends RuntimeException implements IdAwareException {

    private final Object id;

    public AbstractNotFoundException(final String message, final Object id) {
        super(message);
        this.id = id;
    }

    public Object getId() {
        return id;
    }

}
