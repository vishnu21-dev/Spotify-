import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { playlist } from './model/playlist';



@Injectable({
  providedIn: 'root'
})
export class GetPlaylistService {
  url="http://localhost:8090/userTrack/user/getPlaylist"
  url1="http://localhost:8090/userTrack/user/deletePlaylist"
  constructor(private http:HttpClient) { }
  getPlaylist( header:any):Observable<Array<playlist>>{
    return this.http.get<Array<playlist>>(`${this.url}`,{headers:header})

  }
deletetrack(playlistname:string,header:any):Observable<playlist>{
 
return this.http.delete<playlist>(this.url1+"/"+playlistname,{headers:header})
}
}
