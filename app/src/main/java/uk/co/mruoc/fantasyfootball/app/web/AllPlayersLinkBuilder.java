package uk.co.mruoc.fantasyfootball.app.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class AllPlayersLinkBuilder implements LinkBuilder {

    @Override
    public String build(int pageNumber, int pageSize) {
        return linkTo(PlayerController.class)
                .toUriComponentsBuilder()
                .queryParam("pageNumber", pageNumber)
                .queryParam("pageSize", pageSize)
                .toUriString();
    }

}