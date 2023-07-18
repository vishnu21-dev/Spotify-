import { TestBed } from '@angular/core/testing';

import { GetPlaylistService } from './get-playlist.service';

describe('GetPlaylistService', () => {
  let service: GetPlaylistService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetPlaylistService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
