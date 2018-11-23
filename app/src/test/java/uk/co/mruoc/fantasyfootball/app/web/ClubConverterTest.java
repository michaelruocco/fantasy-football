package uk.co.mruoc.fantasyfootball.app.web;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.ClubDocument.ClubDocumentBuilder;
import uk.co.mruoc.fantasyfootball.app.dao.Club;

import static org.assertj.core.api.Assertions.assertThat;

public class ClubConverterTest {

    private static final int PAGE_SIZE = 10;

    private static final long ID = 54321L;
    private static final String NAME = "Test Club";

    private final ClubConverter converter = new ClubConverter();

    @Test
    public void shouldConvertIdToDocument() {
        final Club club = new Club(ID, NAME);

        final ClubDocument document = converter.toDocument(club, PAGE_SIZE);

        assertThat(document.getId()).isEqualTo(club.getId());
    }

    @Test
    public void shouldConvertNameToDocument() {
        final Club club = new Club(ID, NAME);

        final ClubDocument document = converter.toDocument(club, PAGE_SIZE);

        assertThat(document.getName()).isEqualTo(club.getName());
    }

    @Test
    public void shouldPopulateSelfLinkOnDocument() {
        final Club club = new Club(ID, NAME);

        final ClubDocument document = converter.toDocument(club, PAGE_SIZE);

        assertThat(document.getSelfLink()).isEqualTo("/clubs/" + ID);
    }

    @Test
    public void shouldPopulateClubPlayersLinkOnDocument() {
        final Club club = new Club(ID, NAME);

        final ClubDocument document = converter.toDocument(club, PAGE_SIZE);

        assertThat(document.getClubPlayersLink()).isEqualTo("/clubs/" + ID + "/players?pageNumber=0&pageSize=" + PAGE_SIZE);
    }

    @Test
    public void shouldConvertIdFromDocument() {
        final ClubDocument document = new ClubDocumentBuilder()
                .setId(ID)
                .build();

        final Club club = converter.toClub(document);

        assertThat(club.getId()).isEqualTo(document.getId());
    }

    @Test
    public void shouldConvertNameFromDocument() {
        final ClubDocument document = new ClubDocumentBuilder()
                .setName(NAME)
                .build();

        final Club club = converter.toClub(document);

        assertThat(club.getName()).isEqualTo(document.getName());
    }

}
