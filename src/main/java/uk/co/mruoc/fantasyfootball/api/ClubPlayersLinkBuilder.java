package uk.co.mruoc.fantasyfootball.api;

import uk.co.mruoc.fantasyfootball.web.ClubController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class ClubPlayersLinkBuilder {

    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;

    public static String build(long clubId) {
        return build(clubId, DEFAULT_PAGE_NUMBER, DEFAULT_PAGE_SIZE);
    }

    public static String build(long clubId, int pageNumber, int pageSize) {
        return linkTo(ClubController.class)
                .slash(clubId)
                .slash("players")
                .toUriComponentsBuilder()
                .queryParam("pageNumber", pageNumber)
                .queryParam("pageSize", pageSize)
                .toUriString();
    }

}