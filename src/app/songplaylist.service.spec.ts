import { TestBed } from '@angular/core/testing';

import { SongplaylistService } from './songplaylist.service';

describe('SongplaylistService', () => {
  let service: SongplaylistService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SongplaylistService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
