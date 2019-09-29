import { Register } from './register-component/register-component.model';
import { RegisterComponentComponent } from './register-component/register-component.component';
import { Profile } from './profile.model';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
const headerOption = {
 headers: new HttpHeaders({ 'Content-Type': 'application/json'})
}
@Injectable({
 providedIn: 'root'
})
export class ProfileService {
 private _url: string = "http://13.235.105.89:8050/api/v1/user";
 constructor(private http: HttpClient) { }
getByEmailIdForUserService(emailId):any{
 console.log("in getbyemail of reg service"+ emailId);
 return this.http.get<Profile>(`http://13.235.105.89:8050/api/v1/user/${emailId}`);
}
createUser(user:any):Observable<any>{
  console.log(user);
 return this.http.post<Register>(this._url,user)
}
 getProfileDetails():Observable<any>{
   return this.http.get<Profile[]>(this._url,headerOption);
}
updateTheProfile(profile: any):Observable<any> {
  console.log("service active");
  console.log(profile);
 return this.http.put<Profile>(this._url,profile);
}
}
