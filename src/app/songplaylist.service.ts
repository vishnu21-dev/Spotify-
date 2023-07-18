import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { playlist } from './model/playlist';
import { Track } from './model/track';

@Injectable({
  providedIn: 'root'
})
export class SongplaylistService {
  url="http://localhost:8090/userTrack/user/getPlaylistSong"
  url1="http://localhost:8090/userTrack/user/deleteTrack"
  url2="http://localhost:8090/userTrack/getSong"
  url3="http://localhost:8090/userTrack/user/addTrack"
  constructor(private httpClient : HttpClient) { }
  getSongs(playlistname:any,header:any):Observable<Track>{
    return this.httpClient.get<Track>(this.url+"/"+playlistname,{headers:header})
  }
  deletesong(trackId:string,playlistname:string,header:any):Observable<boolean>{
    return this.httpClient.delete<boolean>(this.url1 + "/" + trackId + "/" + playlistname, { headers: header });
  }
  getPaticularSong(songName:string):Observable<Track>{
    return this.httpClient.get<Track>(this.url2+ "/" +songName);

  }
  addSong(playlistname:string,track:Track,header:any):Observable<Track>{
    return this.httpClient.post<Track>(this.url3+"/"+playlistname,track,{headers:header})
  }
  
}
