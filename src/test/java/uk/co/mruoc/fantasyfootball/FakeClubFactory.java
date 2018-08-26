package uk.co.mruoc.fantasyfootball;

import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.dao.Club;

import static uk.co.mruoc.fantasyfootball.api.ClubDocument.*;

public class FakeClubFactory {

    private static final ClubData CLUB_DATA = new FakeClubData();

    public static Club buildClub() {
        return buildClub(CLUB_DATA);
    }

    public static Club buildClub(ClubData data) {
        return new Club(data.getId(), data.getName());
    }

    public static ClubDocument buildClubDocument() {
        return buildClubDocument(CLUB_DATA);
    }

    public static ClubDocument buildClubDocument(ClubData data) {
        return new ClubDocumentBuilder()
                .setId(data.getId())
                .setName(data.getName())
                .build();
    }

}
