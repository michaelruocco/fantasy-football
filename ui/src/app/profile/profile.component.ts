import { Component, OnInit } from '@angular/core';
import { AuthService } from '../auth.service';
import { User } from '../user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: User;

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
