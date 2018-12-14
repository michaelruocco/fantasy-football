import { Injectable, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';
import { filter } from 'rxjs/operators';
import * as auth0 from 'auth0-js';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../environments/environment';

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
    scope: 'openid profile'
  });

  profile: any;
  @Output() profileChange = new EventEmitter<any>();

  constructor(public router: Router, private http: HttpClient) {}

  public login(): void {
    this.auth0.authorize();
  }

  public handleAuthentication(): void {
    console.log('handle authentication');
    this.auth0.parseHash((err, authResult) => {
      if (authResult && authResult.accessToken && authResult.idToken) {
        window.location.hash = '';
        this.setSession(authResult);
        this.getProfile(null);
        this.router.navigate(['/']);
      } else if (err) {
        this.router.navigate(['/']);
        console.log(err);
      }
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

  public getProfile(callback): void {
    const accessToken = localStorage.getItem('access_token');
    if (!accessToken) {
      console.log('no access token found, could not load profile');
      return;
    }

    const self = this;
    this.auth0.client.userInfo(accessToken, (err, profile) => {
      if (profile) {
        console.log('profile updated ' + JSON.stringify(profile));
        this.profile = profile;
        this.profileChange.emit(profile);
      }
      if (callback) {
        callback(err, profile);
      }
    });
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
