import { Component, OnInit, Input } from '@angular/core';
import { Club } from '../club';

@Component({
  selector: 'app-club-detail',
  templateUrl: './club-detail.component.html',
  styleUrls: ['./club-detail.component.css']
})
export class ClubDetailComponent implements OnInit {

  @Input() club: Club;

  constructor() { }

  ngOnInit() {
  }

}
