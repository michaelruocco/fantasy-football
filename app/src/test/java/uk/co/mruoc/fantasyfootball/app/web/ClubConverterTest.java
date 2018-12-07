package uk.co.mruoc.fantasyfootball.app.web;

import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import uk.co.mruoc.fantasyfootball.api.ArrayDocument;
import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.ClubDocument.ClubData;
import uk.co.mruoc.fantasyfootball.api.ClubDocument.ClubDocumentBuilder;
import uk.co.mruoc.fantasyfootball.app.dao.Club;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
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

    @Test
    public void shouldConvertOverriddenIdFromDocument() {
        final ClubDocument document = new ClubDocumentBuilder()
                .setId(ID)
                .build();
        final long overrideId = 9999;

        final Club club = converter.toClub(overrideId, document);

        assertThat(club.getId()).isEqualTo(overrideId);
    }

    @Test
    public void shouldConvertPageToArrayDocument() {
        final int pageNumber = 0;
        final int pageSize = 10;
        final int totalPages = 0;
        Page<Club> page = new PageImpl<>(emptyList(), PageRequest.of(pageNumber, pageSize), totalPages);

        ArrayDocument<ClubData> document = converter.toDocument(page);

        assertThat(document.getPageNumber()).isEqualTo(pageNumber);
        assertThat(document.getPageSize()).isEqualTo(pageSize);
        assertThat(document.getTotalItems()).isEqualTo(0);
        assertThat(document.getTotalItems()).isEqualTo(0);

        assertThat(document.getSelfLink()).isEqualTo("/clubs?pageNumber=0&pageSize=10");
        assertThat(document.getFirstLink()).isEqualTo("/clubs?pageNumber=0&pageSize=10");
        assertThat(document.getLastLink()).isEqualTo("/clubs?pageNumber=0&pageSize=10");
        assertThat(document.getNextLink()).isNull();
        assertThat(document.getPreviousLink()).isNull();
    }

    @Test
    public void shouldConvertPreviousLinkIfPageNumberGreaterThanZero() {
        final int pageNumber = 1;
        final int pageSize = 10;
        final int totalPages = 0;
        Page<Club> page = new PageImpl<>(emptyList(), PageRequest.of(pageNumber, pageSize), totalPages);

        ArrayDocument<ClubData> document = converter.toDocument(page);

        assertThat(document.getSelfLink()).isEqualTo("/clubs?pageNumber=1&pageSize=10");
        assertThat(document.getPreviousLink()).isEqualTo("/clubs?pageNumber=0&pageSize=10");
    }

    @Test
    public void shouldConvertNextLinkIfPageNumberNotOnLastPage() {
        final int pageNumber = 0;
        final int pageSize = 1;
        final int totalPages = 3;
        final List<Club> clubs = Arrays.asList(new Club(1L), new Club(2L), new Club(3L));
        Page<Club> page = new PageImpl<>(clubs, PageRequest.of(pageNumber, pageSize), totalPages);

        ArrayDocument<ClubData> document = converter.toDocument(page);

        assertThat(document.getNextLink()).isEqualTo("/clubs?pageNumber=1&pageSize=1");
    }

}
