import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { UserData } from '../user-data';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: UserData;

  constructor(public authService: AuthService) { }

  ngOnInit() {
    if (this.authService.user) {
      this.user = this.authService.user;
    } else {
      this.authService.getUser((error, user) => {
        this.user = user;
      });
    }
  }

}
