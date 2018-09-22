package uk.co.mruoc.fantasyfootball.api.example;

import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.ClubsDocument;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static uk.co.mruoc.fantasyfootball.api.ClubDocument.ClubDocumentBuilder;
import static uk.co.mruoc.fantasyfootball.api.example.ExampleLinkBuilder.buildClubLink;
import static uk.co.mruoc.fantasyfootball.api.example.ExampleLinkBuilder.buildClubsLink;
import static uk.co.mruoc.fantasyfootball.api.example.ExampleLinkBuilder.calculateLastPage;

public class ExampleClubDocumentFactory {

    private static final int DEFAULT_PAGE_NUMBER = 0;
    private static final int DEFAULT_PAGE_SIZE = 10;

    private static final ClubData CLUB_DATA1 = new ExampleClubData1();
    private static final ClubData CLUB_DATA2 = new ExampleClubData2();

    public static ClubDocument buildClubDocument1() {
        return buildClubDocument(CLUB_DATA1);
    }

    public static ClubDocument buildClubDocument(ClubData data) {
        return buildClubDocument(data, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
    }

    public static ClubDocument buildClubDocument(ClubData data, int pageNumber, int pageSize) {
        long id = data.getId();
        return new ClubDocumentBuilder()
                .setId(data.getId())
                .setName(data.getName())
                .setSelfLink(buildClubLink(data.getId()))
                .setClubPlayersLink(ExampleLinkBuilder.buildClubPlayersLink(id, pageNumber, pageSize))
                .build();
    }

    public static ClubsDocument buildClubsDocument() {
        List<ClubDocument> documents = Arrays.asList(buildClubDocument(CLUB_DATA1), buildClubDocument(CLUB_DATA2));
        return buildClubsDocument(0, 2, 2, 1, documents);
    }

    public static ClubsDocument buildClubsDocumentWithMultiplePages() {
        List<ClubDocument> documents = Arrays.asList(buildClubDocument(CLUB_DATA1), buildClubDocument(CLUB_DATA2));
        return buildClubsDocument(1, 2, 6, 3, documents);
    }

    public static ClubsDocument buildClubsDocumentWithNoData() {
        return buildClubsDocument(0, 2, 0, 0, emptyList());
    }

    public static ClubsDocument buildClubsDocument(int pageNumber, int pageSize, int totalClubs, int totalPages, List<ClubDocument> clubDocuments) {
        ClubsDocument.ClubsDocumentBuilder builder = new ClubsDocument.ClubsDocumentBuilder()
                .setData(toDataList(clubDocuments))
                .setTotalPages(totalPages)
                .setTotalClubs(totalClubs)
                .setPageNumber(pageNumber)
                .setPageSize(pageSize)
                .setSelfLink(buildClubsLink(pageNumber, pageSize))
                .setFirstLink(buildClubsLink(0, pageSize))
                .setLastLink(buildClubsLink(calculateLastPage(totalPages), pageSize));

        if (pageNumber < totalPages - 1) {
            builder.setNextLink(buildClubsLink(pageNumber + 1, pageSize));
        }

        if (pageNumber > 0) {
            builder.setPreviousLink(buildClubsLink(pageNumber - 1, pageSize));
        }

        return builder.build();
    }

    private static List<ClubDocument.ClubData> toDataList(List<ClubDocument> documents) {
        return documents.stream().map(ClubDocument::getData).collect(Collectors.toList());
    }

}
