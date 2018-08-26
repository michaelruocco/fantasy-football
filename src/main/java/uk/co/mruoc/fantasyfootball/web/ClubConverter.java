package uk.co.mruoc.fantasyfootball.web;

import org.springframework.stereotype.Component;
import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.ClubDocument.ClubDocumentBuilder;
import uk.co.mruoc.fantasyfootball.dao.Club;

@Component
public class ClubConverter {

    public ClubDocument toDocument(Club club) {
        return new ClubDocumentBuilder()
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
