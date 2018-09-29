import { Injectable } from '@angular/core';
import { ClubPlayers } from './club-players';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  private clubsUrl = 'http://localhost:8080/clubs/';

  constructor(private http: HttpClient) { }

  getClubPlayers(clubId, pageNumber, pageSize): Observable<ClubPlayers> {
    var url = this.clubsUrl + clubId + '/players?pageNumber=' + (pageNumber - 1) + '&pageSize=' + pageSize;
    console.log('loading club players ' + url);
    return this.http.get(url).pipe(map(body => body as ClubPlayers));
  }

}
