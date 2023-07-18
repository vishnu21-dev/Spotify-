import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { LoggedInUser } from '../model/login';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent  {

  user:LoggedInUser={
    emailId:"",
    password:""
  }
  constructor(private loginuser:LoginService,private route:Router){}
  requestedsong=null
  requestedSongError:boolean=false
  loggedIn(){
    this.loginuser.login(this.user).subscribe(data=>{this.loginuser.isLoggedIn=true
  let headers=new HttpHeaders().set('Authorization','Bearer '+data.token)
  this.loginuser.token=headers;
  console.log(this.loginuser.token.get('Authorization'))},error=>{this.requestedsong=error.error;this.requestedSongError=true} );
setTimeout(() => {
this.route.navigateByUrl("allTracks")
}, 2000);
}
}
