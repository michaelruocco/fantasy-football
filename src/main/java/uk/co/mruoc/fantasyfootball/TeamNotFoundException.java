package uk.co.mruoc.fantasyfootball;

public class TeamNotFoundException extends FantasyFootballException {

    public TeamNotFoundException(String name) {
        super("team not found with name " + name);
    }

}
