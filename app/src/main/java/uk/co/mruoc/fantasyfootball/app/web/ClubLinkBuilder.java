package uk.co.mruoc.fantasyfootball.app.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class ClubLinkBuilder {

    public static String build(long clubId) {
        return linkTo(ClubController.class)
                .slash(clubId)
                .toString();
    }

    public static String build(int pageNumber, int pageSize) {
        return linkTo(ClubController.class)
                .toUriComponentsBuilder()
                .queryParam("pageNumber", pageNumber)
                .queryParam("pageSize", pageSize)
                .toUriString();
    }

}