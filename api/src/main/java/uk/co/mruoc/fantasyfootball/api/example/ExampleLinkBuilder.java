package uk.co.mruoc.fantasyfootball.api.example;

public class ExampleLinkBuilder {

    public static String buildPlayerLink(long id) {
        return "/players/" + id;
    }

    public static String buildClubLink(Long id) {
        return "/clubs/" + id;
    }

    public static String buildClubPlayersLink(long id, int pageNumber, int pageSize) {
        return buildClubLink(id) + "/players" +
                "?pageNumber=" + pageNumber +
                "&pageSize=" + pageSize;
    }

    public static int calculateLastPage(int totalPages) {
        if (totalPages > 0) {
            return totalPages - 1;
        }
        return 0;
    }

}
