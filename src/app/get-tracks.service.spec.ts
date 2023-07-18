import { TestBed } from '@angular/core/testing';

import { GetTracksService } from './get-tracks.service';

describe('GetTracksService', () => {
  let service: GetTracksService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetTracksService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
