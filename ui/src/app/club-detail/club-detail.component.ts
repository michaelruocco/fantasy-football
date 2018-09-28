import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Club } from '../club';
import { Player } from '../player';
import { ClubService }  from '../club.service';
import { PlayerService }  from '../player.service';

@Component({
  selector: 'app-club-detail',
  templateUrl: './club-detail.component.html',
  styleUrls: ['./club-detail.component.css']
})
export class ClubDetailComponent implements OnInit {

  @Input() club: Club;
  players: Player[];

  constructor(
    private route: ActivatedRoute,
    private clubService: ClubService,
    private playerService: PlayerService,
    private location: Location
  ) {}

  ngOnInit() {
    this.getClub();
  }

  getClub(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.clubService.getClub(id).subscribe(club => this.club = club);
    this.playerService.getClubPlayers(id).subscribe(players => this.players = players);
  }

  goBack(): void {
    this.location.back();
  }

}
