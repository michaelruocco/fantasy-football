package uk.co.mruoc.fantasyfootball.api.example;

import uk.co.mruoc.fantasyfootball.api.example.PlayerData;

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
    public String getPosition() {
        return "STRIKER";
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
