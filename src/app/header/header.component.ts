import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { ViewPlaylistComponent } from '../view-playlist/view-playlist.component';
import { GetPlaylistService } from '../get-playlist.service';
import { LoginComponent } from '../login/login.component';
import { LoginService } from '../login.service';
import { Router } from '@angular/router';
import { ThisReceiver } from '@angular/compiler';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent  {
check() {
  if (this.loginService.isLoggedIn && this.loginService.token!=null) {
    this.router.navigateByUrl("createPlaylist")

  } else {
  alert("Login to Create Playlist")
  this.router.navigateByUrl("login")

  }
}

logout() {
this.loginService.logout()
alert("THANK YOU")
this.router.navigateByUrl("login")
}

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver,private loginService:LoginService,private router:Router) {}



checklogin(){
  if(this.loginService.isLoggedIn && this.loginService.token!=null){
    alert(" Already Logged In")

    }
    else
    this.router.navigateByUrl("login")

}
checkregister(){
  if(this.loginService.isLoggedIn && this.loginService.token!=null){
    alert(" Already Logged In")
return true
    }
    else
    this.router.navigateByUrl("register")
    return false
}
isLoginInvalid() {

  if (this.loginService.isLoggedIn && this.loginService.token!=null) {
    return false;
  } else {
    return true;
  }
}
islogOutInvalid() {

  if (this.loginService.isLoggedIn && this.loginService.token!=null) {
    return true;
  } else {
    return false;
  }
}
checkforViewPlaylist(){
  if (this.loginService.isLoggedIn && this.loginService.token!=null) {
    this.router.navigateByUrl("viewPlaylist")

  } else {
  alert("Login to View Playlist")
  this.router.navigateByUrl("login")

  }
}
}

