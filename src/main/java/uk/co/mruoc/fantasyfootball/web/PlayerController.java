package uk.co.mruoc.fantasyfootball.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uk.co.mruoc.fantasyfootball.api.PlayerDocument;
import uk.co.mruoc.fantasyfootball.dao.Player;
import uk.co.mruoc.fantasyfootball.service.PlayerService;

import javax.validation.Valid;

@RestController
@RequestMapping(path= "/players")
public class PlayerController {

    @Autowired
    private final PlayerService service;
    private final PlayerConverter converter;

    public PlayerController(PlayerService service, PlayerConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @PostMapping
    public @ResponseBody PlayerDocument create(@Valid @RequestBody PlayerDocument document) {
        Player player = converter.toPlayer(document);
        Player createdPlayer = service.create(player);
        return converter.toDocument(createdPlayer);
    }

    @PutMapping("/{id}")
    public @ResponseBody PlayerDocument update(@Valid @PathVariable("id") long id, @RequestBody PlayerDocument document) {
        Player player = converter.toPlayer(id, document);
        Player updatedPlayer = service.update(player);
        return converter.toDocument(updatedPlayer);
    }

    @GetMapping("/{id}")
    public @ResponseBody PlayerDocument read(@PathVariable("id") long id) {
        Player player = service.read(id);
        return converter.toDocument(player);
    }

}
