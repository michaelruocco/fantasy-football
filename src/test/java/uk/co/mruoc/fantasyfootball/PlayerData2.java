package uk.co.mruoc.fantasyfootball;

import uk.co.mruoc.fantasyfootball.dao.Position;

public class PlayerData2 implements PlayerData {

    @Override
    public Long getId() {
        return 1122L;
    }

    @Override
    public String getFirstName() {
        return "Michael";
    }

    @Override
    public String getLastName() {
        return "Ruocco";
    }

    @Override
    public Position getPosition() {
        return Position.STRIKER;
    }

    @Override
    public String getPositionName() {
        return getPosition().name();
    }

    @Override
    public int getValue() {
        return 10000000;
    }

    @Override
    public Long getClubId() {
        return 1234L;
    }
}
