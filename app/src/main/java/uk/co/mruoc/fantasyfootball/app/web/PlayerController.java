package uk.co.mruoc.fantasyfootball.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uk.co.mruoc.fantasyfootball.api.ArrayDocument;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument.PlayerData;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.fantasyfootball.app.dao.Player;
import uk.co.mruoc.fantasyfootball.app.service.PlayerService;

import javax.validation.Valid;

@RestController
@RequestMapping(path= "/players")
public class PlayerController {

    private static final String DEFAULT_PAGE_SIZE_STRING = "10";

    private final PlayerService service;
    private final PlayerConverter converter;
    private final ResponseBuilder<PlayerDocument> responseBuilder = new ResponseBuilder<>();

    @Autowired
    public PlayerController(PlayerService service, PlayerConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @PostMapping
    public @ResponseBody ResponseEntity<PlayerDocument> create(@Valid @RequestBody final PlayerDocument document) {
        if (document.hasId() && service.exists(document.getId())) {
            return update(document.getId(), document);
        }
        final Player player = converter.toPlayer(document);
        final Player createdPlayer = service.create(player);
        PlayerDocument createdDocument = converter.toDocument(createdPlayer);
        return responseBuilder.buildCreatedResponse(createdDocument);
    }

    @PutMapping("/{id}")
    public @ResponseBody ResponseEntity<PlayerDocument> update(
            @Valid @PathVariable("id") long id,
            @RequestBody final PlayerDocument document) {
        final Player player = converter.toPlayer(id, document);
        final Player updatedPlayer = service.update(player);
        PlayerDocument updatedDocument = converter.toDocument(updatedPlayer);
        return responseBuilder.buildUpdatedResponse(updatedDocument);
    }

    @GetMapping("/{id}")
    public @ResponseBody PlayerDocument read(@PathVariable("id") long id) {
        final Player player = service.read(id);
        return converter.toDocument(player);
    }

    @GetMapping
    public @ResponseBody ArrayDocument<PlayerData> read(
            @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
            @RequestParam(name = "pageSize", defaultValue = DEFAULT_PAGE_SIZE_STRING) int pageSize) {
        final Page<Player> players = service.read(pageNumber, pageSize);
        return converter.toPlayersDocument(players);
    }

}
