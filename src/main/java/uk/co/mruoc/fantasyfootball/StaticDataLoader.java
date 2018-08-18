package uk.co.mruoc.fantasyfootball;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import uk.co.mruoc.fantasyfootball.dao.Club;
import uk.co.mruoc.fantasyfootball.service.ClubService;

@Component
public class StaticDataLoader implements ApplicationRunner {

    private ClubService clubService;

    public StaticDataLoader(ClubService clubService) {
        this.clubService = clubService;
    }

    @Override
    public void run(ApplicationArguments args) {
        populateFreeAgentClub();
    }

    private void populateFreeAgentClub() {
        clubService.save(new Club(FreeAgentClub.ID, FreeAgentClub.NAME));
        clubService.save(new Club(2, "Leeds United"));
        clubService.save(new Club(3, "Derby County"));
    }

}
