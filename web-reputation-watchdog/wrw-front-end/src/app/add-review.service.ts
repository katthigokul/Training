import { Injectable, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class AddReviewService {
  constructor(private http: HttpClient) {

  }
  public request: any = {
    entityId: '',
    entityTitle: '',
    reviewDescription: '',
    reviewTitle: '',
    reviewedBy: '',
    entityReview: []
  };

  saveReview(title: any, entityId: string, emailId: any, reviewTitle: any,
             reviewDescription: any, aspectBasedRating: any): Observable<any> {
    this.request.reviewTitle = reviewTitle;
    this.request.entityId = entityId;
    this.request.reviewDescription = reviewDescription;
    this.request.entityTitle = title;
    this.request.reviewedBy = emailId;
    this.request.entityReview = aspectBasedRating;
    console.log(this.request);
    return this.http.post('http://13.235.105.89:8060/api/v1/addReview', this.request);
  }
}


