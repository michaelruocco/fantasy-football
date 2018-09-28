import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { Club } from '../club';
import { ClubService } from '../club.service';

@Component({
  selector: 'app-clubs',
  templateUrl: './clubs.component.html',
  styleUrls: ['./clubs.component.css']
})
export class ClubsComponent implements OnInit {

  clubs: Club[];

  constructor(private clubService: ClubService, private router: Router) { }

  ngOnInit() {
    this.getClubs();
  }

  getClubs(): void {
    this.clubService.getClubs().subscribe(clubs => this.clubs = clubs);
  }

  select(club) {
    console.log(club.id)
    this.router.navigate([`./detail/${club.id}`]);
  };

}
