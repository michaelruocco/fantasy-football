package uk.co.mruoc.fantasyfootball.api;

import uk.co.mruoc.fantasyfootball.api.PlayerDocument.PlayerData;

public class PlayersDocument extends ArrayDocument<PlayerData> {

    public PlayersDocument() {
        // required by jackson
    }

    public PlayersDocument(PlayersDocumentBuilder builder) {
        super(builder);
    }

    public static class PlayersDocumentBuilder extends ArrayDocumentBuilder<PlayerData> {

        public ArrayDocument<PlayerData> build() {
            return new PlayersDocument(this);
        }

    }

}
