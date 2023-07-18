import { TestBed } from '@angular/core/testing';

import { CanleavepageGuard } from './canleavepage.guard';

describe('CanleavepageGuard', () => {
  let guard: CanleavepageGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(CanleavepageGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
