package uk.co.mruoc.fantasyfootball.api.example;

import uk.co.mruoc.fantasyfootball.api.example.PlayerData;

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
    public String getPosition() {
        return "MIDFIELDER";
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
