import { Component, OnInit } from '@angular/core';
import { Club } from '../club';
import { ClubService } from '../club.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  clubs: Club[] = [];

  constructor(private clubService: ClubService) { }

  ngOnInit() {
    this.getClubs();
  }

  getClubs(): void {
    this.clubService.getClubs().subscribe(clubs => this.clubs = clubs.splice(0, 5));
  }

}
