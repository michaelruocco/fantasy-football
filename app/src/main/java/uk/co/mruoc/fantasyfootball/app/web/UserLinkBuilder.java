package uk.co.mruoc.fantasyfootball.app.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class UserLinkBuilder {

    public static String build(long id) {
        return linkTo(UserController.class)
                .slash(id)
                .toString();
    }

}