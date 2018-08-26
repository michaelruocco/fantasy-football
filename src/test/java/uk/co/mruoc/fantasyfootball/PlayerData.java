package uk.co.mruoc.fantasyfootball;

import uk.co.mruoc.fantasyfootball.dao.Position;

public interface PlayerData {

    Long getId();

    String getFirstName();

    String getLastName();

    Position getPosition();

    String getPositionName();

    int getValue();

    Long getClubId();

}
