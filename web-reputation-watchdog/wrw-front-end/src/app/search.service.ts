import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
​
@Injectable({
  providedIn: 'root'
})
export class SearchService {
​
  constructor(private http: HttpClient) {}
​
  search(entityName, domain, emailId): Observable<any> {
    return this.http.get(
      `http://13.235.105.89:8000/api/v1/entity/` + entityName + `?emailID=${emailId}&entityCategory=${domain}`
    );
  }
}
