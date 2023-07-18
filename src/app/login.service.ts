import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { LoggedInUser } from './model/login';
import { User } from './model/User';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  urlForRegister="http://localhost:8090/userTrack/addUser"

  isLoggedIn:boolean=false;
  token:HttpHeaders|undefined|null
urlForLogin="http://localhost:8090/userAuth/userLogin"
  constructor(private httpclient:HttpClient) { }
  login(User:LoggedInUser):Observable<any>{
    return this.httpclient.post(`${this.urlForLogin}`,User);
  }

  register(user:User):Observable<User>
  {
    return this.httpclient.post<User>(`${this.urlForRegister}`,user);
  }


   logout()
 {
   this.token=null;

   }
}
