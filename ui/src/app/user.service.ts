import { Injectable } from '@angular/core';
import { User } from './user';
import { UserData } from './user-data';
import { UserAttributes } from './user-attributes';
import { Observable, of, empty } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private usersUrl = environment.apiUrl + '/users';

  constructor(private http: HttpClient) { }

  createUser(profile): Observable<UserData> {
    var url = this.usersUrl;
    var userAttributes: UserAttributes = {
      name: profile.name,
      nickname: profile.nickname,
      picture: profile.picture,
      type: 'STANDARD',
      email: profile.email
    }
    var userData: UserData = {
      type: 'users',
      attributes: userAttributes
    };
    var user: User = {
      data: userData
    }
    console.log('creating user ' + url + ' with user ' + JSON.stringify(user));
    return this.http.post<User>(url, user).pipe(map(body => body['data'] as UserData));
  }

  getUser(email): Observable<UserData> {
    var url = this.usersUrl;
    let params = new HttpParams().set('email', email);
    console.log('loading user ' + url + ' with params ' + JSON.stringify(params));
    return this.http.get(url, {params: params}).pipe(map(body => body['data'] as UserData));
  }

}
