package uk.co.mruoc.fantasyfootball.app.web;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.ClubDocument.ClubData;
import uk.co.mruoc.fantasyfootball.api.ClubDocument.ClubDocumentBuilder;
import uk.co.mruoc.fantasyfootball.api.ClubsDocument;
import uk.co.mruoc.fantasyfootball.api.ClubsDocument.ClubsDocumentBuilder;
import uk.co.mruoc.fantasyfootball.app.dao.Club;

import java.util.List;
import java.util.stream.Collectors;

import static uk.co.mruoc.fantasyfootball.api.example.ExampleLinkBuilder.calculateLastPage;

@Component
public class ClubConverter {

    public ClubsDocument toDocument(final Page<Club> page) {
        final List<ClubData> clubs = page.stream().map(player -> toDocument(player, 10).getData()).collect(Collectors.toList());
        ClubsDocumentBuilder builder = new ClubsDocumentBuilder()
                .setData(clubs)
                .setTotalClubs(page.getTotalElements())
                .setPageNumber(page.getNumber())
                .setPageSize(page.getSize())
                .setTotalPages(page.getTotalPages())
                .setSelfLink(ClubLinkBuilder.build(page.getNumber(), page.getSize()))
                .setFirstLink(PlayersLinkBuilder.build(0, page.getSize()))
                .setLastLink(PlayersLinkBuilder.build(calculateLastPage(page.getTotalPages()), page.getSize()));

        if (page.getNumber() > 0) {
            builder.setPreviousLink(PlayersLinkBuilder.build(page.getNumber() - 1, page.getSize()));
        }

        if (page.getNumber() < page.getTotalPages() - 1) {
            builder.setNextLink(PlayersLinkBuilder.build(page.getNumber() + 1, page.getSize()));
        }

        return builder.build();
    }

    public ClubDocument toDocument(final Club club, final int pageSize) {
        return toDocument(club, 0, pageSize);
    }

    public ClubDocument toDocument(final Club club, final int pageNumber, final int pageSize) {
        Long id = club.getId();
        return new ClubDocumentBuilder()
                .setId(id)
                .setName(club.getName())
                .setSelfLink(ClubLinkBuilder.build(id))
                .setClubPlayersLink(PlayersLinkBuilder.build(id, pageNumber, pageSize))
                .build();
    }

    public Club toClub(final ClubDocument document) {
        return new Club(document.getId(), document.getName());
    }

}
