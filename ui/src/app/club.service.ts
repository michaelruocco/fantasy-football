import { Injectable } from '@angular/core';
import { Club } from './club';
import { CLUBS } from './mock-clubs';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClubService {

  private clubsUrl = 'http://localhost:8080/clubs';

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) { }

  getClubs(): Observable<Club[]> {
    this.messageService.add('ClubService: fetched clubs');
    return this.http.get(this.clubsUrl).pipe(map(body => body["data"] as Club[]));
  }

  getClub(id: number): Observable<Club> {
    this.messageService.add(`ClubService: fetched club id=${id}`);
    var url = this.clubsUrl + "/" + id;
    return this.http.get(url).pipe(map(body => body["data"] as Club));
  }

  private log(message: string) {
    this.messageService.add(`HeroService: ${message}`);
  }

}
