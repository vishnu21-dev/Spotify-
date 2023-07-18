import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { GetTracksService } from '../get-tracks.service';
import { LoginService } from '../login.service';
import { Track } from '../model/track';

@Component({
  selector: 'app-view-song',
  templateUrl: './view-song.component.html',
  styleUrls: ['./view-song.component.css']
})
export class ViewSongComponent {
  tracks:Track|any

  constructor(private loginService:LoginService,private getTrackservice:GetTracksService,private router:Router){
    this.getTrack()
  }
  getTrack(){
    return this.getTrackservice.getTracks().subscribe(data=>this.tracks=data);
  }
  audio=new Audio();
  playSongByUrl(url:string){
    
   if(this.loginService.isLoggedIn && this.loginService.token!=null){
    this.audio.src=url
    this.audio.load();
    this.audio.play();
  }
  else{
    alert("Login to play music ")
this.router.navigateByUrl("login")
  }
}
  pause(){
    this.audio.pause();
  }



}
