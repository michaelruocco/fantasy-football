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
        return user.orElseThrow(() -> new UserEmailNotFoundException(email));
    }

    public User read(final long id) {
        final Optional<User> user = repository.findById(id);
        return user.orElseThrow(() -> new UserIdNotFoundException(id));
    }

    public User create(final User user) {
        return repository.save(user);
    }

    public User update(final User user) {
        if (!user.hasId()) {
            throw new IllegalArgumentException("cannot update user without id");
        }

        final User updatedUser = loadAndUpdate(user);
        return repository.save(updatedUser);
    }

    public void delete(final long id) {
        repository.deleteById(id);
    }

    private User loadAndUpdate(final User user) {
        final Optional<User> loadedUserOpt = repository.findById(user.getId());
        if (!loadedUserOpt.isPresent()) {
            throw new UserIdNotFoundException(user.getId());
        }
        final User loadedUser = loadedUserOpt.get();
        loadedUser.update(user);
        return loadedUser;
    }

}
