package uk.co.mruoc.fantasyfootball.client;

import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;

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
        List<ClubDocument> clubs = clubCsvLoader.load("data/clubs.csv");
        clubs.forEach(client::create);
    }

    public void loadPlayers() {
        List<PlayerDocument> players = playerCsvLoader.load("data/players.csv");
        players.forEach(client::create);
    }

    public static void main(String[] args) {
        DataLoader loader = new DataLoader();
        loader.load();
    }

}
