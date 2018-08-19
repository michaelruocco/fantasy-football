package uk.co.mruoc.fantasyfootball.client;

import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;

public interface Client {

    ClubDocument create(ClubDocument clubDocument);

    PlayerDocument create(PlayerDocument playerDocument);

}
