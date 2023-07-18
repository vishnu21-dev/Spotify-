import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { playlist } from './model/playlist';
import { Track } from './model/track';

@Injectable({
  providedIn: 'root'
})
export class PlaylistService {

  url="http://localhost:8090/userTrack/user/createPlaylist"

  constructor(private http:HttpClient) { }
  create(list:playlist, header:any):Observable<playlist>{
    return this.http.post<playlist>(`${this.url}`,list,{headers:header})

  }
  
}
