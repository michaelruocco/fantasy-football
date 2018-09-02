package uk.co.mruoc.fantasyfootball;

import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.dao.Club;

import static uk.co.mruoc.fantasyfootball.api.ClubDocument.*;

public class FakeClubFactory {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;

    private static final ClubData CLUB_DATA1 = new FakeClubData1();

    public static Club buildClub1() {
        return buildClub(CLUB_DATA1);
    }

    public static Club buildClub(ClubData data) {
        return new Club(data.getId(), data.getName());
    }

    public static ClubDocument buildClubDocument1() {
        return buildClubDocument(CLUB_DATA1);
    }

    public static ClubDocument buildClubDocument(ClubData data) {
        return buildClubDocument(data, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
    }

    public static ClubDocument buildClubDocument(int pageNumber, int pageSize) {
        return buildClubDocument(CLUB_DATA1, pageNumber, pageSize);
    }

    public static ClubDocument buildClubDocument(ClubData data, int pageNumber, int pageSize) {
        return new ClubDocumentBuilder()
                .setId(data.getId())
                .setName(data.getName())
                .setPlayersPageNumber(pageNumber)
                .setPlayersPageSize(pageSize)
                .build();
    }

}
