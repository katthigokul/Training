import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { FormGroup } from '@angular/forms';

@Injectable({
  providedIn: 'root'
})

export class UserService {
  constructor(private httpClient: HttpClient) {}
  authenticate(value: FormGroup): Observable<any> {
      console.log(value);
      return this.httpClient.post('http://13.235.105.89:8082/authenticate', value);
  }
  isUserLoggedIn() {
  }
  logOut() {
    // tslint:disable-next-line:semicolon
    sessionStorage.removeItem('emailId')
  }
}
