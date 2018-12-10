package uk.co.mruoc.fantasyfootball.app.web;

import uk.co.mruoc.fantasyfootball.api.UserDocument;
import uk.co.mruoc.fantasyfootball.app.dao.User;
import uk.co.mruoc.fantasyfootball.app.dao.UserType;

public class UserConverter {

    public User toUser(UserDocument document) {
        final User user = new User();
        user.setId(document.getId());
        user.setFirstName(document.getFirstName());
        user.setLastName(document.getLastName());
        user.setType(UserType.valueOf(document.getUserType()));
        user.setEmail(document.getEmail());
        return user;
    }

    public User toUser(long id, UserDocument document) {
        final User user = toUser(document);
        user.setId(id);
        return user;
    }

}
