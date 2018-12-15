package uk.co.mruoc.fantasyfootball.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import uk.co.mruoc.fantasyfootball.api.UserDocument;
import uk.co.mruoc.fantasyfootball.app.dao.User;
import uk.co.mruoc.fantasyfootball.app.service.UserService;

import javax.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping(path= "/users")
public class UserController {

    private final UserService service;
    private final UserConverter converter;
    private final ResponseBuilder<UserDocument> responseBuilder = new ResponseBuilder<>();

    @Autowired
    public UserController(UserService service, UserConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @PostMapping
    public @ResponseBody ResponseEntity<UserDocument> create(@Valid @RequestBody final UserDocument document) {
        final boolean exists = document.hasEmail() && service.existsByEmail(document.getEmail());
        final User user = converter.toUser(document);
        final User createdUser = service.create(user);
        UserDocument createdDocument = converter.toDocument(createdUser);
        if (exists) {
            return responseBuilder.buildUpdatedResponse(createdDocument);
        }
        return responseBuilder.buildCreatedResponse(createdDocument);
    }

    @PatchMapping("/{id}")
    public @ResponseBody ResponseEntity<UserDocument> update(
            @Valid @PathVariable("id") final long id,
            @RequestBody final UserDocument document) {
        final User user = converter.toUser(id, document);
        final User updatedUser = service.update(user);
        UserDocument updatedDocument = converter.toDocument(updatedUser);
        return responseBuilder.buildUpdatedResponse(updatedDocument);
    }

    @GetMapping("/{id}")
    public @ResponseBody UserDocument read(@PathVariable("id") long id) {
        final User user = service.read(id);
        return converter.toDocument(user);
    }

    @GetMapping
    public @ResponseBody UserDocument read(@RequestParam("email") final String email) {
        final User user = service.readByEmail(email);
        return converter.toDocument(user);
    }

}
