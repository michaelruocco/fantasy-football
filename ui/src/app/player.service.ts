import { Injectable } from '@angular/core';
import { Player } from './player';
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

  getClubPlayers(clubId: number): Observable<Player[]> {
    this.messageService.add('PlayerService: fetched club players');
    return this.http.get(this.clubsUrl + clubId + "/players?pageSize=30").pipe(map(body => body["data"] as Player[]));
  }

}
