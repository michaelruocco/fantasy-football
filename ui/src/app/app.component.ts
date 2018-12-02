import { Component } from '@angular/core';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'Fantasy Football';
  navbarCollapsed = false;

  _subscription;
  profile: any;

  constructor(public authService: AuthService) {
    this._subscription = authService.profileChange.subscribe((profile) => {
      this.profile = profile;
    });
  }

  ngOnInit() {
    if (this.authService.profile) {
      this.profile = this.authService.profile;
    } else {
      this.authService.getProfile((err, profile) => {
        this.profile = profile;
      });
    }
  }

  ngOnDestroy() {
    this._subscription.unsubscribe();
  }

}
