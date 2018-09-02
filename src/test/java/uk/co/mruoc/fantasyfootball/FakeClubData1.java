package uk.co.mruoc.fantasyfootball;

public class FakeClubData1 implements ClubData {

    @Override
    public Long getId() {
        return 1234L;
    }

    @Override
    public String getName() {
        return "Fake Club 1";
    }

}
