package uk.co.mruoc.fantasyfootball.api;

import uk.co.mruoc.fantasyfootball.web.PlayerController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class PlayerLinkBuilder {

    public static String build(long playerId) {
        return linkTo(PlayerController.class)
                .slash(playerId)
                .toString();
    }

}