package uk.co.mruoc.fantasyfootball.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uk.co.mruoc.fantasyfootball.api.ClubDocument;
import uk.co.mruoc.fantasyfootball.app.service.ClubService;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping(path= "/clubs")
@Profile("test")
public class DeleteClubController {

    private final ClubService service;
    private final ResponseBuilder<ClubDocument> responseBuilder = new ResponseBuilder<>();

    @Autowired
    public DeleteClubController(ClubService service) {
        this.service = service;
    }

    @DeleteMapping
    public @ResponseBody ResponseEntity<Void> delete() {
        service.deleteAll();
        return responseBuilder.buildDeletedResponse();
    }

}
