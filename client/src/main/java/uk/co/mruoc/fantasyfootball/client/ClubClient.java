package uk.co.mruoc.fantasyfootball.client;

import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;

public interface ClubClient {

    ClubDocument create(ClubDocument clubDocument);

}
