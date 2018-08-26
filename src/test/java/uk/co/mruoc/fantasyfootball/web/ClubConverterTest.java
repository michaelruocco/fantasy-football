package uk.co.mruoc.fantasyfootball.web;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.ClubDocument.ClubDocumentBuilder;
import uk.co.mruoc.fantasyfootball.dao.Club;

import static org.assertj.core.api.Assertions.assertThat;

public class ClubConverterTest {

    private static final long ID = 6565;
    private static final String NAME = "Test Club";

    private final ClubConverter converter = new ClubConverter();

    @Test
    public void shouldConvertIdToDocument() {
        Club club = new Club(ID);

        ClubDocument document = converter.toDocument(club);

        assertThat(document.getId()).isEqualTo(ID);
    }

    @Test
    public void shouldConvertNameToDocument() {
        Club club = new Club(ID, NAME);

        ClubDocument document = converter.toDocument(club);

        assertThat(document.getName()).isEqualTo(NAME);
    }

    @Test
    public void shouldConvertIdFromDocument() {
        ClubDocument document = new ClubDocumentBuilder().setId(ID).build();

        Club club = converter.toClub(document);

        assertThat(club.getId()).isEqualTo(ID);
    }

    @Test
    public void shouldConvertNameFromDocument() {
        ClubDocument document = new ClubDocumentBuilder().setId(ID).setName(NAME).build();

        Club club = converter.toClub(document);

        assertThat(club.getName()).isEqualTo(NAME);
    }

}
