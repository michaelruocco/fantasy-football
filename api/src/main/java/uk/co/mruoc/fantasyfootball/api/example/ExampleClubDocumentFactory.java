package uk.co.mruoc.fantasyfootball.api.example;

import uk.co.mruoc.fantasyfootball.api.ClubDocument;

import static uk.co.mruoc.fantasyfootball.api.ClubDocument.ClubDocumentBuilder;

public class ExampleClubDocumentFactory {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;

    private static final ClubData CLUB_DATA1 = new ExampleClubData1();

    public static ClubDocument buildClubDocument1() {
        return buildClubDocument(CLUB_DATA1);
    }

    public static ClubDocument buildClubDocument(ClubData data) {
        return buildClubDocument(data, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
    }

    public static ClubDocument buildClubDocument(ClubData data, int pageNumber, int pageSize) {
        long id = data.getId();
        return new ClubDocumentBuilder()
                .setId(data.getId())
                .setName(data.getName())
                .setSelfLink(ExampleLinkBuilder.buildClubLink(data.getId()))
                .setClubPlayersLink(ExampleLinkBuilder.buildClubPlayersLink(id, pageNumber, pageSize))
                .build();
    }

}
