package uk.co.mruoc.fantasyfootball.app.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class PlayersLinkBuilder {

    public static String build(long clubId, long pageNumber, long pageSize) {
        return linkTo(ClubController.class)
                .slash(clubId)
                .slash("players")
                .toUriComponentsBuilder()
                .queryParam("pageNumber", pageNumber)
                .queryParam("pageSize", pageSize)
                .toUriString();
    }

    public static String build(long pageNumber, long pageSize) {
        return linkTo(PlayerController.class)
                .toUriComponentsBuilder()
                .queryParam("pageNumber", pageNumber)
                .queryParam("pageSize", pageSize)
                .toUriString();
    }

}