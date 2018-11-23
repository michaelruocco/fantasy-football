package uk.co.mruoc.fantasyfootball.api;

import uk.co.mruoc.fantasyfootball.api.ClubDocument.ClubData;

public class ClubsDocument extends ArrayDocument<ClubData> {

    public ClubsDocument() {
        // required by jackson
    }

    public ClubsDocument(ClubsDocumentBuilder builder) {
        super(builder);
    }

    public static class ClubsDocumentBuilder extends ArrayDocumentBuilder<ClubData> {

        public ArrayDocument<ClubData> build() {
            return new ClubsDocument(this);
        }

    }

}
