import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../login.service';
import { User } from '../model/User';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  user:User={
    emailId: "",
    name: "",
    address: "",
    password:""
  }
  requestedsong=null
  requestedSongError:boolean=false
  constructor(private loginService:LoginService,private router:Router){}
  registerUser() {
 this.loginService.register(this.user).subscribe(data=>{alert("Welcome to Beats")},error=>{this.requestedsong=error.error;this.requestedSongError=true})

 setTimeout(() => {
  this.router.navigateByUrl("login");
}, 3000);

}

}
