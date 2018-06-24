package uk.co.mruoc.fantasyfootball;

public class FantasyFootballException extends RuntimeException {

    public FantasyFootballException(Throwable cause) {
        super(cause);
    }

    public FantasyFootballException(String message) {
        super(message);
    }

}
