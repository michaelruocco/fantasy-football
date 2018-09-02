package uk.co.mruoc.fantasyfootball.web;

import org.springframework.stereotype.Component;
import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.ClubDocument.ClubDocumentBuilder;
import uk.co.mruoc.fantasyfootball.dao.Club;

@Component
public class ClubConverter {

    public ClubDocument toDocument(final Club club, final String pageSize) {
        return toDocument(club, Integer.parseInt(pageSize));
    }

    public ClubDocument toDocument(final Club club, final int pageSize) {
        return new ClubDocumentBuilder()
                .setId(club.getId())
                .setName(club.getName())
                .setPlayersPageSize(pageSize)
                .build();
    }

    public Club toClub(final ClubDocument document) {
        return new Club(document.getId(), document.getName());
    }

}
