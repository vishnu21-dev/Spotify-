import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { playlist } from './model/playlist';
import { Track } from './model/track';

@Injectable({
  providedIn: 'root'
})
export class GetTracksService {
  url="http://localhost:8090/userTrack/getAllTracks1"
  constructor(private http:HttpClient) {
    this.getTracks()
  }
  getTracks():Observable<Track>{
    return this.http.get<Track>(`${this.url}`)

  }
}
