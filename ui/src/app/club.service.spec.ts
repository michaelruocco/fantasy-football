import { TestBed } from '@angular/core/testing';

import { ClubService } from './club.service';

describe('ClubService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ClubService = TestBed.get(ClubService);
    expect(service).toBeTruthy();
  });
});
