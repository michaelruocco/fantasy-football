package uk.co.mruoc.fantasyfootball.app.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class ClubPlayersLinkBuilder implements PlayersLinkBuilder {

    private final long clubId;

    public ClubPlayersLinkBuilder(long clubId) {
        this.clubId = clubId;
    }

    @Override
    public String build(int pageNumber, int pageSize) {
        return linkTo(ClubController.class)
                .slash(clubId)
                .slash("players")
                .toUriComponentsBuilder()
                .queryParam("pageNumber", pageNumber)
                .queryParam("pageSize", pageSize)
                .toUriString();
    }

}