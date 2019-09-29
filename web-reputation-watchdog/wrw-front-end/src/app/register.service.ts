import { HttpClient } from '@angular/common/http';

import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
  providedIn: "root"
})
export class RegisterService {
  constructor(private http: HttpClient) {}
  public request: any = {};
  onSubmit(
    firstName: any,
    lastName: any,
    emailId: any,
    password: any,
    mobileNumber: any
  ):

  Observable<any> {
    console.log("message");
    this.request.firstName = firstName;
    this.request.lastName = lastName;
    this.request.emailId = emailId;
    this.request.password = password;
    this.request.mobileNumber = mobileNumber;
    console.log(this.request);
    return this.http.post("http://13.235.105.89:8050/api/v1/user", this.request);
  }
}
