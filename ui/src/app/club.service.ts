import { Injectable } from '@angular/core';
import { Club } from './club';
import { CLUBS } from './mock-clubs';
import { Observable, of } from 'rxjs';
import { MessageService } from './message.service';

@Injectable({
  providedIn: 'root'
})
export class ClubService {

  constructor(private messageService: MessageService) { }

  getClubs(): Observable<Club[]> {
    this.messageService.add('ClubService: fetched clubs');
    return of(CLUBS);
  }

}
