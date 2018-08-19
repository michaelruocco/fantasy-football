package uk.co.mruoc.fantasyfootball.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.mruoc.fantasyfootball.dao.Player;
import uk.co.mruoc.fantasyfootball.dao.PlayerRepository;

import java.util.Optional;

@Component
public class PlayerService {

    @Autowired
    private PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public Player create(Player player) {
        return repository.save(player);
    }

    public Player update(Player player) {
        if (!player.hasId()) {
            throw new IllegalArgumentException("cannot update player with id not set");
        }

        if (!repository.existsById(player.getId())) {
            throw new PlayerNotFoundException(player.getId());
        }

        return repository.save(player);
    }

    public Player read(long id) {
        Optional<Player> player = repository.findById(id);
        return player.orElseThrow(() -> new PlayerNotFoundException(id));
    }

}
