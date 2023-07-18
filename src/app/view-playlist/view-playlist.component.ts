import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { GetPlaylistService } from '../get-playlist.service';
import { LoginService } from '../login.service';
import { playlist } from '../model/playlist';
import { Track } from '../model/track';

import { SongplaylistService } from '../songplaylist.service';

@Component({
  selector: 'app-view-playlist',
  templateUrl: './view-playlist.component.html',
  styleUrls: ['./view-playlist.component.css']
})
export class ViewPlaylistComponent implements OnInit {

playlists:playlist|any|null
  id:any|null
foundTrack:Track|any
constructor(private loginService:LoginService,private playlistservice:GetPlaylistService,
  private router:Router,private ar:ActivatedRoute,private track:SongplaylistService,private getService:GetPlaylistService){
this.viewPlaylist()
}
  ngOnInit(): void {
    this.ar.paramMap.subscribe((parameter)=>{
      this.id = parameter.get('id')??0; })

  }

viewPlaylist() {
  if(this.loginService.isLoggedIn&& this.loginService.token!=null){
   this.playlistservice.getPlaylist(this.loginService.token).subscribe(data=>{this.playlists=data},error=>{this.requestedsong=error.error;this.requestedSongError=true});


}
else
{
 
this.router.navigateByUrl("header")


}
}


requestedsong=null
requestedSongError:boolean=false


add(playlistname:string){

  this.track.getPaticularSong(this.id).subscribe(data=>
    { this.track.addSong(playlistname,data,this.loginService.token).
      subscribe(data=>{alert("song added")},error=>{this.requestedsong=error.error;this.requestedSongError=true})})
setTimeout(() => {
  this.router.navigateByUrl("allTracks")
}, 1500);

}

deletePlaylist(playlistname:string) {
if(this.id==0){

this.getService.deletetrack(playlistname,this.loginService.token).subscribe(data=>(alert("deleted")))
this.router.navigateByUrl("allTracks")
}



}

}
