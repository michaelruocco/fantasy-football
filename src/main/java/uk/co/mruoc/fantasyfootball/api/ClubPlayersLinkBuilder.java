package uk.co.mruoc.fantasyfootball.api;

import uk.co.mruoc.fantasyfootball.web.ClubController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class ClubPlayersLinkBuilder {

    public static String build(long clubId, long pageNumber, long pageSize) {
        return linkTo(ClubController.class)
                .slash(clubId)
                .slash("players")
                .toUriComponentsBuilder()
                .queryParam("pageNumber", pageNumber)
                .queryParam("pageSize", pageSize)
                .toUriString();
    }

}