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
import uk.co.mruoc.fantasyfootball.dao.Player;
import uk.co.mruoc.fantasyfootball.service.PlayerService;

import javax.validation.Valid;

@RestController
@RequestMapping(path= "/players")
public class PlayerController {

    private final PlayerConverter playerConverter = new PlayerConverter();

    @Autowired
    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public @ResponseBody PlayerDocument create(@Valid @RequestBody PlayerDocument document) {
        Player player = playerConverter.toPlayer(document);
        Player createdPlayer = playerService.create(player);
        return playerConverter.toDocument(createdPlayer);
    }

    @PutMapping("/{id}")
    public @ResponseBody PlayerDocument update(@Valid @PathVariable("id") long id, @RequestBody PlayerDocument document) {
        Player player = playerConverter.toPlayer(id, document);
        Player createdPlayer = playerService.update(player);
        return playerConverter.toDocument(createdPlayer);
    }

    @GetMapping("/{id}")
    public @ResponseBody PlayerDocument read(@PathVariable("id") long id) {
        Player player = playerService.read(id);
        return playerConverter.toDocument(player);
    }

}
