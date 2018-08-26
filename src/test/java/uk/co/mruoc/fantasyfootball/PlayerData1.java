package uk.co.mruoc.fantasyfootball;

import uk.co.mruoc.fantasyfootball.dao.Position;

public class PlayerData1 implements PlayerData {

    @Override
    public Long getId() {
        return 1133L;
    }

    @Override
    public String getFirstName() {
        return "Joe";
    }

    @Override
    public String getLastName() {
        return "Bloggs";
    }

    @Override
    public Position getPosition() {
        return Position.DEFENDER;
    }

    @Override
    public String getPositionName() {
        return getPosition().name();
    }

    @Override
    public int getValue() {
        return 50000000;
    }

    @Override
    public Long getClubId() {
        return 1234L;
    }
}
