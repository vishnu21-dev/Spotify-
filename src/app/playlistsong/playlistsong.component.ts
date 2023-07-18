import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../login.service';
import { playlist } from '../model/playlist';
import { Track } from '../model/track';
import { SongplaylistService } from '../songplaylist.service';

@Component({
  selector: 'app-playlistsong',
  templateUrl: './playlistsong.component.html',
  styleUrls: ['./playlistsong.component.css']
})
export class PlaylistsongComponent implements OnInit {

  audio=new Audio();
  playSongByUrl(url: any) {

  this.audio.src=url
  this.audio.load();
  this.audio.play();
}
pause(){
  this.audio.pause();
}
tracks:Track|any
constructor( private playlistservices:SongplaylistService,private ar:ActivatedRoute,private login:LoginService, private route:Router){}
  id:any
ngOnInit(): void {
  this.ar.paramMap.subscribe((parameter)=>{
     this.id = parameter.get('id')??0;

    this.playlistservices.getSongs(this.id,this.login.token).subscribe(data=>{this.tracks=data},
      error=>{this.requestedsong=error.error;this.requestedSongError=true})
  })
}
requestedsong=null
  requestedSongError:boolean=false
deleteSong(trackname:string){
  this.playlistservices.deletesong(trackname,this.id,this.login.token).subscribe(data=>(alert("deleted")));
  this.route.navigateByUrl("viewPlaylist")
}

}
