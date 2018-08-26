package uk.co.mruoc.fantasyfootball;

import uk.co.mruoc.fantasyfootball.dao.Position;

public class PlayerDataWithoutClub implements PlayerData {

    @Override
    public Long getId() {
        return 1144L;
    }

    @Override
    public String getFirstName() {
        return "Homer";
    }

    @Override
    public String getLastName() {
        return "Simpson";
    }

    @Override
    public Position getPosition() {
        return Position.MIDFIELDER;
    }

    @Override
    public String getPositionName() {
        return getPosition().name();
    }

    @Override
    public int getValue() {
        return 5000000;
    }

    @Override
    public Long getClubId() {
        return null;
    }
}
