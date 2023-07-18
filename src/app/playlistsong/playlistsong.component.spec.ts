import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlaylistsongComponent } from './playlistsong.component';

describe('PlaylistsongComponent', () => {
  let component: PlaylistsongComponent;
  let fixture: ComponentFixture<PlaylistsongComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PlaylistsongComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PlaylistsongComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
