package uk.co.mruoc.fantasyfootball.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.co.mruoc.fantasyfootball.web.ClubController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class ClubPlayersLinkBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClubPlayersLinkBuilder.class);

    public static String build(long clubId, int pageNumber, int pageSize) {
        try {
            return linkTo(ClubController.class)
                    .slash(clubId)
                    .slash("players")
                    .toUriComponentsBuilder()
                    .queryParam("pageNumber", pageNumber)
                    .queryParam("pageSize", pageSize)
                    .toUriString();
        } catch (IllegalStateException e) {
            LOGGER.debug(e.getMessage(), e);
            return "";
        }
    }

}