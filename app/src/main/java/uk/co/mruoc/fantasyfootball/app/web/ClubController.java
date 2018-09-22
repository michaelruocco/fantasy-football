package uk.co.mruoc.fantasyfootball.app.web;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import uk.co.mruoc.fantasyfootball.api.ClubsDocument;
import uk.co.mruoc.fantasyfootball.api.PlayersDocument;
import uk.co.mruoc.fantasyfootball.app.dao.Club;
import uk.co.mruoc.fantasyfootball.app.dao.Player;
import uk.co.mruoc.fantasyfootball.app.service.ClubService;

import javax.validation.Valid;

@RestController
@RequestMapping(path= "/clubs")
public class ClubController {

    private static final String DEFAULT_PAGE_SIZE_STRING = "10";
    private static final int DEFAULT_PAGE_SIZE = Integer.parseInt(DEFAULT_PAGE_SIZE_STRING);

    private final ClubService service;
    private final ClubConverter clubConverter;
    private final PlayerConverter playerConverter;
    private final ResponseBuilder<ClubDocument> responseBuilder = new ResponseBuilder<>();

    public ClubController(ClubService service) {
        this(service, new ClubConverter(), new PlayerConverter());
    }

    @Autowired
    public ClubController(ClubService service, ClubConverter clubConverter, PlayerConverter playerConverter) {
        this.service = service;
        this.clubConverter = clubConverter;
        this.playerConverter = playerConverter;
    }

    @PostMapping
    @ApiOperation(value = "Create a club", response = ClubDocument.class)
    public @ResponseBody ResponseEntity<ClubDocument> create(@ApiParam(value = "ClubDocument", required = true, name = "ClubDocument") @Valid @RequestBody final ClubDocument document) {
        final Club club = clubConverter.toClub(document);
        final Club createdClub = service.upsert(club);
        final ClubDocument createdDocument = clubConverter.toDocument(createdClub, DEFAULT_PAGE_SIZE);
        return responseBuilder.buildCreatedResponse(createdDocument);
    }

    @GetMapping
    public @ResponseBody ClubsDocument read(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                            @RequestParam(name = "pageSize", defaultValue = DEFAULT_PAGE_SIZE_STRING) int pageSize) {
        final Page<Club> clubs = service.read(pageNumber, pageSize);
        return clubConverter.toDocument(clubs);
    }

    @GetMapping("/{id}")
    public @ResponseBody ClubDocument read(@PathVariable("id") long id) {
        final Club club = service.read(id);
        return clubConverter.toDocument(club, DEFAULT_PAGE_SIZE);
    }

    @GetMapping("/{id}/players")
    public @ResponseBody
    PlayersDocument readPlayers(@PathVariable("id") long id,
                                @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                @RequestParam(name = "pageSize", defaultValue = DEFAULT_PAGE_SIZE_STRING) int pageSize) {
        final Page<Player> players = service.readPlayersByClubId(id, pageNumber, pageSize);
        return playerConverter.toClubPlayersDocument(id, players);
    }

}
