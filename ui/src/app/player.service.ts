import { Injectable } from '@angular/core';
import { ClubPlayers } from './club-players';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PlayerService {

  private clubsUrl = 'http://localhost:8080/clubs/';

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) { }

  getClubPlayers(clubId, pageNumber, pageSize): Observable<ClubPlayers> {
    var url = this.clubsUrl + clubId + '/players?pageNumber=' + pageNumber + '&pageSize=' + pageSize;
    this.messageService.add('PlayerService: fetching club players ' + url);
    return this.http.get(url).pipe(map(body => body as ClubPlayers));
  }

}
