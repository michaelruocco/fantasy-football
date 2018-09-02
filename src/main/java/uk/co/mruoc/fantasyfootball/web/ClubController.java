package uk.co.mruoc.fantasyfootball.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.api.ClubPlayersDocument;
import uk.co.mruoc.fantasyfootball.dao.Club;
import uk.co.mruoc.fantasyfootball.dao.Player;
import uk.co.mruoc.fantasyfootball.service.ClubService;

import javax.validation.Valid;

@RestController
@RequestMapping(path= "/clubs")
public class ClubController {

    private static final String DEFAULT_PAGE_SIZE = "10";

    private final ClubService service;
    private final ClubConverter clubConverter;
    private final PlayerConverter playerConverter;
    private final CreatedResponseBuilder<ClubDocument> createdResponseBuilder = new CreatedResponseBuilder<>();

    @Autowired
    public ClubController(ClubService service, ClubConverter clubConverter, PlayerConverter playerConverter) {
        this.service = service;
        this.clubConverter = clubConverter;
        this.playerConverter = playerConverter;
    }

    @PostMapping
    public @ResponseBody ResponseEntity<ClubDocument> create(@Valid @RequestBody final ClubDocument document) {
        final Club club = clubConverter.toClub(document);
        final Club createdClub = service.upsert(club);
        final ClubDocument createdDocument = clubConverter.toDocument(createdClub, DEFAULT_PAGE_SIZE);
        return createdResponseBuilder.build(createdDocument);
    }

    @GetMapping("/{id}")
    public @ResponseBody ClubDocument read(@PathVariable("id") long id) {
        final Club club = service.read(id);
        return clubConverter.toDocument(club, DEFAULT_PAGE_SIZE);
    }

    @GetMapping("/{id}/players")
    public @ResponseBody ClubPlayersDocument readPlayers(@PathVariable("id") long id,
                                    @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                    @RequestParam(name = "pageSize", defaultValue = DEFAULT_PAGE_SIZE) int pageSize) {
        final Page<Player> players = service.readPlayersByClubId(id, pageNumber, pageSize);
        return playerConverter.toDocument(id, players);
    }

}
