package uk.co.mruoc.fantasyfootball.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.mruoc.fantasyfootball.web.ClubController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class ClubLinkBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClubLinkBuilder.class);

    public static String build(long clubId) {
        try {
            return linkTo(ClubController.class)
                    .slash(clubId)
                    .toString();
        } catch (IllegalStateException e) {
            LOGGER.debug(e.getMessage(), e);
            return "";
        }
    }

}