package uk.co.mruoc.fantasyfootball.api.example;

import uk.co.mruoc.fantasyfootball.api.example.ClubData;

public class ExampleClubData1 implements ClubData {

    @Override
    public Long getId() {
        return 1234L;
    }

    @Override
    public String getName() {
        return "Fake Club 1";
    }

}
