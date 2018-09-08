package uk.co.mruoc.fantasyfootball.app.web;

import org.springframework.stereotype.Component;
import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.ClubDocument.ClubDocumentBuilder;
import uk.co.mruoc.fantasyfootball.app.dao.Club;

@Component
public class ClubConverter {

    public ClubDocument toDocument(final Club club, final int pageSize) {
        return toDocument(club, 0, pageSize);
    }

    public ClubDocument toDocument(final Club club, final int pageNumber, final int pageSize) {
        Long id = club.getId();
        return new ClubDocumentBuilder()
                .setId(id)
                .setName(club.getName())
                .setSelfLink(ClubLinkBuilder.build(id))
                .setClubPlayersLink(ClubPlayersLinkBuilder.build(id, pageNumber, pageSize))
                .build();
    }

    public Club toClub(final ClubDocument document) {
        return new Club(document.getId(), document.getName());
    }

}
