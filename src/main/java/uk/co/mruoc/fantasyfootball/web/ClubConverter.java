package uk.co.mruoc.fantasyfootball.web;

import uk.co.mruoc.fantasyfootball.dao.Club;

public class ClubConverter {

    public ClubDocument toDocument(Club club) {
        return new ClubDocument.ClubDocumentBuilder()
                .setId(club.getId())
                .setName(club.getName())
                .build();
    }

    public Club toClub(ClubDocument document) {
        Club club = new Club();
        club.setId(document.getId());
        club.setName(document.getName());
        return club;
    }

}
