package uk.co.mruoc.fantasyfootball.client.dataload;

import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.fantasyfootball.client.Client;
import uk.co.mruoc.fantasyfootball.client.DefaultClient;

import java.util.List;

public class DataLoader {

    private final ClubCsvLoader clubCsvLoader = new ClubCsvLoader();
    private final PlayerCsvLoader playerCsvLoader = new PlayerCsvLoader();

    private final Client client = new DefaultClient("http://localhost:8080");

    private void load() {
        loadClubs();
        loadPlayers();
    }

    public void loadClubs() {
        final List<ClubDocument> clubs = clubCsvLoader.load("data/clubs.csv");
        clubs.forEach(client::create);
    }

    public void loadPlayers() {
        final List<PlayerDocument> players = playerCsvLoader.load("data/players.csv");
        players.forEach(client::create);
    }

    public static void main(String[] args) {
        final DataLoader loader = new DataLoader();
        loader.load();
    }

}
