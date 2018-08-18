package uk.co.mruoc.fantasyfootball.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    //@Query("select p from player p where p.club = ?1 order by p.position")
    Page<Player> findByClub(Club club, Pageable pageable);

}
