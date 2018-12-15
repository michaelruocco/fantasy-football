package uk.co.mruoc.fantasyfootball.app.web;

import org.springframework.stereotype.Component;
import uk.co.mruoc.fantasyfootball.api.UserDocument;
import uk.co.mruoc.fantasyfootball.api.UserDocument.UserDocumentBuilder;
import uk.co.mruoc.fantasyfootball.app.dao.User;
import uk.co.mruoc.fantasyfootball.app.dao.UserType;

@Component
public class UserConverter {

    public User toUser(UserDocument document) {
        final User user = new User();
        user.setId(document.getId());
        user.setName(document.getName());
        user.setNickname(document.getNickname());
        user.setPicture(document.getPicture());
        user.setType(UserType.valueOf(document.getUserType()));
        user.setEmail(document.getEmail());
        return user;
    }

    public User toUser(long id, UserDocument document) {
        final User user = toUser(document);
        user.setId(id);
        return user;
    }

    public UserDocument toDocument(User user) {
        return new UserDocumentBuilder()
                .setId(user.getId())
                .setName(user.getName())
                .setNickname(user.getNickname())
                .setPicture(user.getPicture())
                .setType(user.getType().name())
                .setEmail(user.getEmail())
                .setSelfLink(UserLinkBuilder.build(user.getId()))
                .build();
    }

}
