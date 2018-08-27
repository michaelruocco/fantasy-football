package uk.co.mruoc.fantasyfootball.web;

import org.junit.Test;
import uk.co.mruoc.fantasyfootball.FakeClubFactory;
import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.dao.Club;

import static org.assertj.core.api.Assertions.assertThat;

public class ClubConverterTest {

    private final ClubConverter converter = new ClubConverter();

    @Test
    public void shouldConvertIdToDocument() {
        final Club club = FakeClubFactory.buildClub();

        final ClubDocument document = converter.toDocument(club);

        assertThat(document.getId()).isEqualTo(club.getId());
    }

    @Test
    public void shouldConvertNameToDocument() {
        final Club club = FakeClubFactory.buildClub();

        final ClubDocument document = converter.toDocument(club);

        assertThat(document.getName()).isEqualTo(club.getName());
    }

    @Test
    public void shouldConvertIdFromDocument() {
        final ClubDocument document = FakeClubFactory.buildClubDocument();

        final Club club = converter.toClub(document);

        assertThat(club.getId()).isEqualTo(document.getId());
    }

    @Test
    public void shouldConvertNameFromDocument() {
        final ClubDocument document = FakeClubFactory.buildClubDocument();

        final Club club = converter.toClub(document);

        assertThat(club.getName()).isEqualTo(document.getName());
    }

}
