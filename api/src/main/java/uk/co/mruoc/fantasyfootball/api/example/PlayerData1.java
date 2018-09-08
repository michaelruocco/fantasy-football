package uk.co.mruoc.fantasyfootball.api.example;

import uk.co.mruoc.fantasyfootball.api.example.PlayerData;

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
    public String getPosition() {
        return "DEFENDER";
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
