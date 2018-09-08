package uk.co.mruoc.fantasyfootball.app.web;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument.PlayerData;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument.PlayerDocumentBuilder;
import uk.co.mruoc.fantasyfootball.api.ClubPlayersDocument;
import uk.co.mruoc.fantasyfootball.api.ClubPlayersDocument.ClubPlayersDocumentBuilder;
import uk.co.mruoc.fantasyfootball.app.dao.Club;
import uk.co.mruoc.fantasyfootball.app.dao.Player;
import uk.co.mruoc.fantasyfootball.app.dao.Position;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PlayerConverter {

    public PlayerDocument toDocument(Player player) {
        final PlayerDocumentBuilder builder = new PlayerDocumentBuilder()
                .setId(player.getId())
                .setFirstName(player.getFirstName())
                .setLastName(player.getLastName())
                .setPosition(player.getPosition().name())
                .setValue(player.getValue())
                .setSelfLink(PlayerLinkBuilder.build(player.getId()));

        final Optional<Long> clubId = player.getClubId();
        if (clubId.isPresent()) {
            builder.setClubId(clubId.get());
            builder.setClubLink(ClubLinkBuilder.build(clubId.get()));
        }
        return builder.build();
    }

    public ClubPlayersDocument toDocument(long clubId, Page<Player> page) {
        final List<PlayerData> players = page.stream().map(player -> toDocument(player).getData()).collect(Collectors.toList());
        ClubPlayersDocumentBuilder builder = new ClubPlayersDocumentBuilder()
                .setClubId(clubId)
                .setData(players)
                .setTotalPlayers(page.getTotalElements())
                .setPageNumber(page.getNumber())
                .setPageSize(page.getSize())
                .setTotalPages(page.getTotalPages())
                .setSelfLink(ClubPlayersLinkBuilder.build(clubId, page.getNumber(), page.getSize()))
                .setFirstLink(ClubPlayersLinkBuilder.build(clubId, 0, page.getSize()))
                .setLastLink(ClubPlayersLinkBuilder.build(clubId, calculateLastPage(page.getTotalPages()), page.getSize()));

        if (page.getNumber() > 0) {
            builder.setPreviousLink(ClubPlayersLinkBuilder.build(clubId, page.getNumber() - 1, page.getSize()));
        }

        if (page.getNumber() < page.getTotalPages() - 1) {
            builder.setNextLink(ClubPlayersLinkBuilder.build(clubId, page.getNumber() + 1, page.getSize()));
        }

        return builder.build();
    }

    public Player toPlayer(long id, PlayerDocument document) {
        final Player player = toPlayer(document);
        player.setId(id);
        return player;
    }

    public Player toPlayer(PlayerDocument document) {
        final Player player = new Player();
        player.setId(document.getId());
        player.setFirstName(document.getFirstName());
        player.setLastName(document.getLastName());
        player.setPosition(Position.valueOf(document.getPosition()));
        player.setValue(document.getValue());
        player.setClub(extractClub(document));
        return player;
    }

    private Club extractClub(PlayerDocument document) {
        final Optional<Long> clubId = document.getClubId();
        return clubId.map(Club::new).orElse(null);
    }

    private int calculateLastPage(int totalPages) {
        if (totalPages > 0) {
            return totalPages - 1;
        }
        return 0;
    }

}
