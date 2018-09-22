package uk.co.mruoc.fantasyfootball.app.web;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.ClubsDocument;
import uk.co.mruoc.fantasyfootball.api.example.ExampleClubDocumentFactory;
import uk.co.mruoc.fantasyfootball.app.dao.Club;

import java.util.List;
import java.util.stream.Collectors;

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
        final ClubDocument document = ExampleClubDocumentFactory.buildClubDocument1();

        final Club club = converter.toClub(document);

        assertThat(club.getId()).isEqualTo(document.getId());
    }

    @Test
    public void shouldConvertNameFromDocument() {
        final ClubDocument document = ExampleClubDocumentFactory.buildClubDocument1();

        final Club club = converter.toClub(document);

        assertThat(club.getName()).isEqualTo(document.getName());
    }

    @Test
    public void shouldConvertPageOfClubsIntoClubsDocument() {
        final ClubsDocument expectedDocument = ExampleClubDocumentFactory.buildClubsDocument();
        final Page<Club> page = new PageImpl<>(toClubs(expectedDocument.getData()), PageRequest.of(0, 2), 2);

        final ClubsDocument document = converter.toDocument(page);

        assertThat(document).isEqualToComparingFieldByFieldRecursively(expectedDocument);
    }

    @Test
    public void shouldSetPreviousLinkWhenConvertingPageOfClubsIntoClubsDocumentIfNotFirstPage() {
        final ClubsDocument expectedDocument = ExampleClubDocumentFactory.buildClubsDocumentWithMultiplePages();
        final Page<Club> page = new PageImpl<>(toClubs(expectedDocument.getData()), PageRequest.of(1, 2), 6);

        final ClubsDocument document = converter.toDocument(page);

        assertThat(document).isEqualToComparingFieldByFieldRecursively(expectedDocument);
    }

    @Test
    public void shouldConvertSpecificId() {
        final long id = 9191;
        final ClubDocument document = ExampleClubDocumentFactory.buildClubDocument1();

        final Club club = converter.toClub(id, document);

        assertThat(club.getId()).isEqualTo(id);
    }

    private static List<Club> toClubs(List<ClubDocument.ClubData> clubDataList) {
        return clubDataList.stream().map(ClubConverterTest::toClub).collect(Collectors.toList());
    }

    private static Club toClub(ClubDocument.ClubData data) {
        return new Club(data.getId(), data.getName());
    }

}
