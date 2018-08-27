package uk.co.mruoc.fantasyfootball.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.mruoc.fantasyfootball.dao.Player;
import uk.co.mruoc.fantasyfootball.dao.PlayerRepository;

import java.util.Optional;

@Component
public class PlayerService {

    @Autowired
    private final PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public Player read(long id) {
        Optional<Player> player = repository.findById(id);
        return player.orElseThrow(() -> new PlayerNotFoundException(id));
    }

    public Player upsert(Player player) {
        return repository.save(player);
    }

    public Player update(Player player) {
        if (!player.hasId()) {
            throw new IllegalArgumentException("cannot update player without id");
        }

        if (!repository.existsById(player.getId())) {
            throw new PlayerNotFoundException(player.getId());
        }

        return repository.save(player);
    }

}
