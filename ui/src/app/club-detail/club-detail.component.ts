import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Club } from '../club';
import { ClubService }  from '../club.service';

@Component({
  selector: 'app-club-detail',
  templateUrl: './club-detail.component.html',
  styleUrls: ['./club-detail.component.css']
})
export class ClubDetailComponent implements OnInit {

  @Input() club: Club;

  constructor(
    private route: ActivatedRoute,
    private clubService: ClubService,
    private location: Location
  ) {}

  ngOnInit() {
    this.getClub();
  }

  getClub(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.clubService.getClub(id).subscribe(club => this.club = club);
  }

  goBack(): void {
    this.location.back();
  }

}
