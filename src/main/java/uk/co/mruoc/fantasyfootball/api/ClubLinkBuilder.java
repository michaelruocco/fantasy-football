package uk.co.mruoc.fantasyfootball.api;

import uk.co.mruoc.fantasyfootball.web.ClubController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class ClubLinkBuilder {

    public static String build(long clubId) {
        return linkTo(ClubController.class)
                .slash(clubId)
                .toString();
    }

}