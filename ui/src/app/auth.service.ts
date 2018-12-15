import { Injectable, EventEmitter, Output, NgZone } from '@angular/core';
import { Router } from '@angular/router';
import { filter } from 'rxjs/operators';
import * as auth0 from 'auth0-js';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../environments/environment';
import { UserService } from './user.service';
import { User } from './user';

(window as any).global = window;

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  auth0 = new auth0.WebAuth({
    clientID: '8OUsTwlZ9T4qv5LgfAs7hFFegBwIh5oT',
    domain: 'michaelruocco.eu.auth0.com',
    responseType: 'token id_token',
    redirectUri: environment.loginCallbackUrl + '/login/callback',
    scope: 'openid profile email'
  });

  user: User;
  @Output() userChange = new EventEmitter<User>();

  constructor(public router: Router,
    private http: HttpClient,
    private ngZone: NgZone,
    private userService: UserService) {}

  public login(): void {
    this.auth0.authorize();
  }

  public handleAuthentication(): void {
    console.log('handle authentication');
    this.auth0.parseHash((err, authResult) => {
      if (authResult && authResult.accessToken && authResult.idToken) {
        window.location.hash = '';
        this.setSession(authResult);
        this.getUser(null);
      } else if (err) {
        console.log(err);
      }
      this.ngZone.run(() => {
        this.router.navigate(['/']);
      });
    });
  }

  public logout(): void {
    // Remove tokens and expiry time from localStorage
    localStorage.removeItem('access_token');
    localStorage.removeItem('id_token');
    localStorage.removeItem('expires_at');
    // Go back to the home route
    this.router.navigate(['/']);
  }

  public isAuthenticated(): boolean {
    // Check whether the current time is past the
    // Access Token's expiry time
    const expiresAt = JSON.parse(localStorage.getItem('expires_at') || '{}');
    let authenticated = new Date().getTime() < expiresAt;
    return authenticated;
  }

  public getUser(callback): void {
    const accessToken = localStorage.getItem('access_token');
    if (!accessToken) {
      console.log('no access token found, could not load profile');
      return;
    }

    const self = this;
    this.auth0.client.userInfo(accessToken, (error, profile) => {
      if (profile) {
        console.log('profile updated ' + JSON.stringify(profile));
        this.userService.getUser(profile.email).subscribe(user => {
          this.updateUser(user, error, callback);
        }, err => {
          if (err.status === 404) {
            this.userService.createUser(profile).subscribe(user => {
              this.updateUser(user, error, callback);
            });
          } else {
            console.log('error loading user ' + err)
          }
        });
      }
    });
  }

  private updateUser(user, error, callback): void {
    console.log('setting user ' + JSON.stringify(user));
    this.userChange.emit(user);
    if (callback) {
      callback(error, user);
    }
  }

  private setSession(authResult): void {
    var headers = new HttpHeaders();
    // Set the time that the Access Token will expire at
    const expiresAt = JSON.stringify((authResult.expiresIn * 1000) + new Date().getTime());
    localStorage.setItem('access_token', authResult.accessToken);
    localStorage.setItem('id_token', authResult.idToken);
    localStorage.setItem('expires_at', expiresAt);
  }

}
