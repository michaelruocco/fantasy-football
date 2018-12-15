import { Component } from '@angular/core';
import { AuthService } from './auth.service';
import { environment } from '../environments/environment';
import { User } from './user';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'Fantasy Football';
  navbarCollapsed = false;
  apiUrl = environment.apiUrl;

  _subscription;
  user: User;

  constructor(public authService: AuthService) {
    this._subscription = authService.userChange.subscribe((user) => {
      this.user = user;
    });
  }

  ngOnInit() {
    if (this.authService.user) {
      this.user = this.authService.user;
    } else {
      this.authService.getUser((err, user) => {
        this.user = user;
      });
    }
  }

  ngOnDestroy() {
    this._subscription.unsubscribe();
  }

}
