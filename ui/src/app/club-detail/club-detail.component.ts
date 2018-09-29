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
  totalPages: number;
  totalPlayers: number;
  pageSize: number;
  pageNumber: number;

  constructor(
    private route: ActivatedRoute,
    private clubService: ClubService,
    private playerService: PlayerService,
    private location: Location
  ) {}

  ngOnInit() {
    this.loadClub();
  }

  loadClub(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.pageSize = 5;
    this.clubService.getClub(id).subscribe(club => this.setClub(club));
  }

  setClub(club): void {
    this.club = club;
    this.pageNumber = 1;
    this.loadPlayers();
  }

  loadPlayers(): void {
    console.log('loading player page ' + (this.pageNumber - 1));
    this.playerService.getClubPlayers(this.club.id, this.pageNumber - 1, this.pageSize).subscribe(object => this.updateClub(object));
  }

  updateClub(object): void {
    this.players = object.data;

    this.totalPlayers = object.meta.totalPlayers;
    this.pageSize = object.meta.pageSize;
    this.totalPages = object.meta.totalPages;
  }

  goBack(): void {
    this.location.back();
  }

}
