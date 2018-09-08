package uk.co.mruoc.fantasyfootball.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import uk.co.mruoc.fantasyfootball.app.dao.Player;
import uk.co.mruoc.fantasyfootball.app.dao.PlayerRepository;

import java.util.Optional;

@Component
public class PlayerService {

    @Autowired
    private final PlayerRepository repository;

    public PlayerService(final PlayerRepository repository) {
        this.repository = repository;
    }

    public Page<Player> read(final int pageNumber, final int pageSize) {
        final Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return repository.findAll(pageable);
    }

    public Player read(final long id) {
        final Optional<Player> player = repository.findById(id);
        return player.orElseThrow(() -> new PlayerNotFoundException(id));
    }

    public Player upsert(final Player player) {
        return repository.save(player);
    }

    public Player update(final Player player) {
        if (!player.hasId()) {
            throw new IllegalArgumentException("cannot update player without id");
        }

        if (!repository.existsById(player.getId())) {
            throw new PlayerNotFoundException(player.getId());
        }

        return repository.save(player);
    }

}
