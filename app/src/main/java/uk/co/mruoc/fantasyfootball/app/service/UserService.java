package uk.co.mruoc.fantasyfootball.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.mruoc.fantasyfootball.app.dao.User;
import uk.co.mruoc.fantasyfootball.app.dao.UserRepository;

import java.util.Optional;

@Component
public class UserService {

    @Autowired
    private final UserRepository repository;

    public UserService(final UserRepository repository) {
        this.repository = repository;
    }

    public boolean existsByEmail(final String email) {
        return repository.existsByEmail(email);
    }

    public User readByEmail(final String email) {
        final Optional<User> user = repository.findByEmail(email);
        return user.orElseThrow(() -> new UserNotFoundException(email));
    }

    public User create(final User user) {
        return repository.save(user);
    }

    public User update(final User user) {
        if (!user.hasEmail()) {
            throw new IllegalArgumentException("cannot update user without email");
        }

        if (!repository.existsByEmail(user.getEmail())) {
            throw new UserNotFoundException(user.getEmail());
        }

        return repository.save(user);
    }

}
