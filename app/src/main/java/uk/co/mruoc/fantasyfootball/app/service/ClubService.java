package uk.co.mruoc.fantasyfootball.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import uk.co.mruoc.fantasyfootball.app.dao.Club;
import uk.co.mruoc.fantasyfootball.app.dao.ClubRepository;
import uk.co.mruoc.fantasyfootball.app.dao.Player;
import uk.co.mruoc.fantasyfootball.app.dao.PlayerRepository;

import java.util.Optional;

@Component
public class ClubService {

    @Autowired
    private final ClubRepository clubRepository;

    @Autowired
    private final PlayerRepository playerRepository;

    public ClubService(final ClubRepository clubRepository, final PlayerRepository playerRepository) {
        this.clubRepository = clubRepository;
        this.playerRepository = playerRepository;
    }

    public Page<Club> read(int pageNumber, int pageSize) {
        final Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return clubRepository.findAll(pageable);
    }

    public Club read(final long id) {
        final Optional<Club> club = clubRepository.findById(id);
        return club.orElseThrow(() -> new ClubNotFoundException(id));
    }

    public Club create(final Club club) {
        return clubRepository.save(club);
    }

    public Club update(final Club club) {
        if (!club.hasId()) {
            throw new IllegalArgumentException("cannot update club without id");
        }

        if (!clubRepository.existsById(club.getId())) {
            throw new ClubNotFoundException(club.getId());
        }

        return clubRepository.save(club);
    }

    public Page<Player> readPlayersByClubId(long clubId, int pageNumber, int pageSize) {
        final Pageable pageable = PageRequest.of(pageNumber, pageSize);
        final Club club = new Club(clubId);
        return playerRepository.findByClub(club, pageable);
    }

    public boolean exists(final long id) {
        return clubRepository.existsById(id);
    }

    public void deleteAll() {
        clubRepository.deleteAll();
    }

}
