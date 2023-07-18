import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { GetTracksService } from '../get-tracks.service';
import { LoginService } from '../login.service';
import { playlist } from '../model/playlist';
import { Track } from '../model/track';
import { PlaylistService } from '../playlist.service';

@Component({
  selector: 'app-create-playlist',
  templateUrl: './create-playlist.component.html',
  styleUrls: ['./create-playlist.component.css']
})
export class CreatePlaylistComponent {
  usertracks:Track|any
  list:playlist={
    name: ""
  }
  constructor(private playlist:PlaylistService ,private login:LoginService,private router:Router,private trackService:GetTracksService){
    this.getTracks()
  }
  requestedsong=null
  requestedSongError:boolean=false
  addPlaylist(){
if(this.login.isLoggedIn && this.login.token!=null){
  console.log(this.list)
   this.playlist.create(this.list,this.login.token).subscribe(data=>{alert("created")}
   ,error=>{this.requestedsong=error.error;this.requestedSongError=true});
setTimeout(() => {
  this.router.navigateByUrl("allTracks")
}, 1500);

}
else{
  alert("Please Login")
  this.router.navigateByUrl("login")
}

  }
getTracks(){
  this.trackService.getTracks().subscribe(data=>this.usertracks=data)
}
}
