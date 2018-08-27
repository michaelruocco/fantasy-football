package uk.co.mruoc.fantasyfootball.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import uk.co.mruoc.fantasyfootball.dao.Club;
import uk.co.mruoc.fantasyfootball.dao.ClubRepository;
import uk.co.mruoc.fantasyfootball.dao.Player;
import uk.co.mruoc.fantasyfootball.dao.PlayerRepository;

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

    public Club read(final long id) {
        final Optional<Club> club = clubRepository.findById(id);
        return club.orElseThrow(() -> new ClubNotFoundException(id));
    }

    public Club upsert(final Club club) {
        return clubRepository.save(club);
    }

    public Page<Player> readPlayersByClubId(long clubId, int pageNumber, int pageSize) {
        final Pageable pageable = PageRequest.of(pageNumber, pageSize);
        final Club club = new Club(clubId);
        return playerRepository.findByClub(club, pageable);
    }

}
