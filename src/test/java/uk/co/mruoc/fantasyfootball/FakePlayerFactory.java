package uk.co.mruoc.fantasyfootball;

import uk.co.mruoc.fantasyfootball.api.ClubPlayersDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.fantasyfootball.dao.Club;
import uk.co.mruoc.fantasyfootball.dao.Player;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static uk.co.mruoc.fantasyfootball.api.PlayerDocument.PlayerDocumentBuilder;

public class FakePlayerFactory {

    private static final ClubData CLUB_DATA = new FakeClubData();

    private static final PlayerData PLAYER_DATA1 = new PlayerData1();
    private static final PlayerData PLAYER_DATA2 = new PlayerData2();
    private static final PlayerData PLAYER_DATA_WITHOUT_CLUB = new PlayerDataWithoutClub();

    public static List<Player> buildPlayers() {
        return Arrays.asList(buildPlayer1(), buildPlayer2());
    }
    public static Player buildPlayer1() {
        return buildPlayer(PLAYER_DATA1);
    }

    public static Player buildPlayer2() {
        return buildPlayer(PLAYER_DATA2);
    }

    public static Player buildPlayerWithoutClub() {
        return buildPlayer(PLAYER_DATA_WITHOUT_CLUB);
    }

    public static Player buildPlayer(PlayerData data) {
        Player player = new Player();
        player.setId(data.getId());
        player.setFirstName(data.getFirstName());
        player.setLastName(data.getLastName());
        player.setPosition(data.getPosition());
        player.setValue(data.getValue());
        player.setClub(new Club(data.getClubId()));
        return player;
    }

    public static ClubPlayersDocument buildClubPlayersDocument() {
        List<PlayerDocument> documents = Arrays.asList(buildPlayerDocument1(), buildPlayerDocument2());
        return buildClubPlayersDocument(0, 2, 2, 1, CLUB_DATA.getId(), documents);
    }

    public static ClubPlayersDocument buildClubPlayersDocumentWithMultiplePages() {
        List<PlayerDocument> documents = Arrays.asList(buildPlayerDocument1(), buildPlayerDocument2());
        return buildClubPlayersDocument(1, 2, 6, 3, CLUB_DATA.getId(), documents);
    }

    public static ClubPlayersDocument buildClubPlayersDocumentWithNoData() {
        return buildClubPlayersDocument(0, 2, 0, 0, CLUB_DATA.getId(), emptyList());
    }

    public static ClubPlayersDocument buildClubPlayersDocument(int pageNumber, int pageSize, int totalPlayers, int totalPages, long clubId, List<PlayerDocument> playerDocuments) {
        return new ClubPlayersDocument.PlayersDocumentBuilder()
                .setClubId(clubId)
                .setData(toDataList(playerDocuments))
                .setTotalPlayers(totalPlayers)
                .setPageNumber(pageNumber)
                .setPageSize(pageSize)
                .setTotalPages(totalPages)
                .build();
    }

    public static PlayerDocument buildPlayerDocument1() {
        return buildPlayerDocument(PLAYER_DATA1);
    }

    public static PlayerDocument buildPlayerDocument2() {
        return buildPlayerDocument(PLAYER_DATA2);
    }

    public static PlayerDocument buildPlayerDocumentWithoutClub() {
        return buildPlayerDocument(PLAYER_DATA_WITHOUT_CLUB);
    }

    public static PlayerDocument buildPlayerDocument(PlayerData data) {
        return new PlayerDocumentBuilder()
                .setId(data.getId())
                .setFirstName(data.getFirstName())
                .setLastName(data.getLastName())
                .setPosition(data.getPositionName())
                .setValue(data.getValue())
                .setClubId(data.getClubId())
                .build();
    }

    private static List<PlayerDocument.Data> toDataList(List<PlayerDocument> documents) {
        return documents.stream().map(PlayerDocument::getData).collect(Collectors.toList());
    }

}
