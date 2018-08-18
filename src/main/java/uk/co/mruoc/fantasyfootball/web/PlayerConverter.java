package uk.co.mruoc.fantasyfootball.web;

import org.springframework.data.domain.Page;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument.Data;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument.PlayerDocumentBuilder;
import uk.co.mruoc.fantasyfootball.api.PlayersDocument;
import uk.co.mruoc.fantasyfootball.dao.Club;
import uk.co.mruoc.fantasyfootball.dao.Player;
import uk.co.mruoc.fantasyfootball.FreeAgentClub;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlayerConverter {

    public PlayerDocument toDocument(Player player) {
        return new PlayerDocumentBuilder()
                .setId(player.getId())
                .setFirstName(player.getFirstName())
                .setLastName(player.getLastName())
                .setPosition(player.getPosition())
                .setValue(player.getValue())
                .setClubId(player.getClubId())
                .build();
    }

    public PlayersDocument toDocument(long clubId, Page<Player> page) {
        List<Data> players = page.stream().map(player -> toDocument(player).getData()).collect(Collectors.toList());
        return new PlayersDocument.PlayersDocumentBuilder()
                .setClubId(clubId)
                .setData(players)
                .setTotalPlayers(page.getTotalElements())
                .setPageNumber(page.getNumber())
                .setTotalPages(page.getTotalPages())
                .build();
    }

    public Player toPlayer(long id, PlayerDocument document) {
        Player player = toPlayer(document);
        player.setId(id);
        return player;
    }

    public Player toPlayer(PlayerDocument document) {
        Player player = new Player();
        player.setId(document.getId());
        player.setFirstName(document.getFirstName());
        player.setLastName(document.getLastName());
        player.setPosition(document.getPosition());
        player.setValue(document.getValue());
        player.setClub(new Club(extractClubId(document)));
        return player;
    }

    private long extractClubId(PlayerDocument document) {
        Optional<Long> clubId = document.getClubId();
        return clubId.orElse(FreeAgentClub.ID);
    }

}
