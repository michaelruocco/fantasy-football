import { Injectable } from '@angular/core';
import { Club } from './club';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from '../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ClubService {

  private clubsUrl = environment.apiUrl + '/clubs';

  constructor(private http: HttpClient) { }

  getClubs(): Observable<Club[]> {
    console.log('loading clubs ' + this.clubsUrl);
    return this.http.get(this.clubsUrl).pipe(map(body => body['data'] as Club[]));
  }

  getClub(id: number): Observable<Club> {
    var url = this.clubsUrl + '/' + id;
    console.log('loading club ' + url);
    return this.http.get(url).pipe(map(body => body['data'] as Club));
  }

}
