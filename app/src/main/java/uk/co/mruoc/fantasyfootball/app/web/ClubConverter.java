package uk.co.mruoc.fantasyfootball.app.web;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import uk.co.mruoc.fantasyfootball.api.ArrayDocument;
import uk.co.mruoc.fantasyfootball.api.ArrayDocument.ArrayDocumentBuilder;
import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.ClubDocument.ClubData;
import uk.co.mruoc.fantasyfootball.api.ClubDocument.ClubDocumentBuilder;
import uk.co.mruoc.fantasyfootball.api.ClubsDocument.ClubsDocumentBuilder;
import uk.co.mruoc.fantasyfootball.app.dao.Club;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClubConverter {

    public ArrayDocument<ClubData> toDocument(final Page<Club> page) {
        final List<ClubData> clubs = page.stream().map(player -> toDocument(player, page.getSize()).getData()).collect(Collectors.toList());
        ArrayDocumentBuilder<ClubData> builder = new ClubsDocumentBuilder()
                .setData(clubs)
                .setTotalItems(page.getTotalElements())
                .setPageNumber(page.getNumber())
                .setPageSize(page.getSize())
                .setTotalPages(page.getTotalPages())
                .setSelfLink(ClubLinkBuilder.build(page.getNumber(), page.getSize()))
                .setFirstLink(ClubLinkBuilder.build(0, page.getSize()))
                .setLastLink(ClubLinkBuilder.build(LastPageCalculator.calculate(page.getTotalPages()), page.getSize()));

        if (page.getNumber() > 0) {
            builder.setPreviousLink(ClubLinkBuilder.build(page.getNumber() - 1, page.getSize()));
        }

        System.out.println(page.getNumber() + " " + page.getTotalPages());
        if (page.getNumber() < page.getTotalPages() - 1) {
            builder.setNextLink(ClubLinkBuilder.build(page.getNumber() + 1, page.getSize()));
        }

        return builder.build();
    }

    public ClubDocument toDocument(final Club club, final int pageSize) {
        return toDocument(club, 0, pageSize);
    }

    public ClubDocument toDocument(final Club club, final int pageNumber, final int pageSize) {
        Long id = club.getId();
        PlayersLinkBuilder linkBuilder = new ClubPlayersLinkBuilder(id);
        return new ClubDocumentBuilder()
                .setId(id)
                .setName(club.getName())
                .setSelfLink(ClubLinkBuilder.build(id))
                .setClubPlayersLink(linkBuilder.build(pageNumber, pageSize))
                .build();
    }

    public Club toClub(long id, ClubDocument document) {
        final Club club = toClub(document);
        club.setId(id);
        return club;
    }

    public Club toClub(final ClubDocument document) {
        return new Club(document.getId(), document.getName());
    }

}
