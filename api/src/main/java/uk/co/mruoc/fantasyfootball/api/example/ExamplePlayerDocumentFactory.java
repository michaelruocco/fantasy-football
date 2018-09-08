package uk.co.mruoc.fantasyfootball.api.example;

import uk.co.mruoc.fantasyfootball.api.PlayersDocument;
import uk.co.mruoc.fantasyfootball.api.PlayersDocument.ClubPlayersDocumentBuilder;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static uk.co.mruoc.fantasyfootball.api.example.ExampleLinkBuilder.buildClubLink;
import static uk.co.mruoc.fantasyfootball.api.example.ExampleLinkBuilder.buildClubPlayersLink;
import static uk.co.mruoc.fantasyfootball.api.example.ExampleLinkBuilder.buildPlayerLink;
import static uk.co.mruoc.fantasyfootball.api.example.ExampleLinkBuilder.calculateLastPage;
import static uk.co.mruoc.fantasyfootball.api.PlayerDocument.PlayerDocumentBuilder;

public class ExamplePlayerDocumentFactory {

    private static final ClubData CLUB_DATA = new ExampleClubData1();

    private static final PlayerData PLAYER_DATA1 = new PlayerData1();
    private static final PlayerData PLAYER_DATA2 = new PlayerData2();
    private static final PlayerData PLAYER_DATA_WITHOUT_CLUB = new PlayerDataWithoutClub();

    public static PlayersDocument buildClubPlayersDocument() {
        List<PlayerDocument> documents = Arrays.asList(buildPlayerDocument1(), buildPlayerDocument2());
        return buildClubPlayersDocument(0, 2, 2, 1, CLUB_DATA.getId(), documents);
    }

    public static PlayersDocument buildClubPlayersDocumentWithMultiplePages() {
        List<PlayerDocument> documents = Arrays.asList(buildPlayerDocument1(), buildPlayerDocument2());
        return buildClubPlayersDocument(1, 2, 6, 3, CLUB_DATA.getId(), documents);
    }

    public static PlayersDocument buildClubPlayersDocumentWithNoData() {
        return buildClubPlayersDocument(0, 2, 0, 0, CLUB_DATA.getId(), emptyList());
    }

    public static PlayersDocument buildClubPlayersDocument(int pageNumber, int pageSize, int totalPlayers, int totalPages, long clubId, List<PlayerDocument> playerDocuments) {
        ClubPlayersDocumentBuilder builder = new ClubPlayersDocumentBuilder()
                .setClubId(clubId)
                .setData(toDataList(playerDocuments))
                .setTotalPages(totalPages)
                .setTotalPlayers(totalPlayers)
                .setPageNumber(pageNumber)
                .setPageSize(pageSize)
                .setSelfLink(buildClubPlayersLink(clubId, pageNumber, pageSize))
                .setFirstLink(buildClubPlayersLink(clubId, 0, pageSize))
                .setLastLink(buildClubPlayersLink(clubId, calculateLastPage(totalPages), pageSize));

        if (pageNumber < totalPages - 1) {
            builder.setNextLink(buildClubPlayersLink(clubId, pageNumber + 1, pageSize));
        }

        if (pageNumber > 0) {
            builder.setPreviousLink(buildClubPlayersLink(clubId, pageNumber - 1, pageSize));
        }

        return builder.build();
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
                .setPosition(data.getPosition())
                .setValue(data.getValue())
                .setClubId(data.getClubId())
                .setSelfLink(buildPlayerLink(data.getId()))
                .setClubLink(buildClubLink(data.getClubId()))
                .build();
    }

    private static List<PlayerDocument.PlayerData> toDataList(List<PlayerDocument> documents) {
        return documents.stream().map(PlayerDocument::getData).collect(Collectors.toList());
    }

}
