import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { log } from 'util';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  // getReviewDetails(title: string) {
  //   throw new Error("Method not implemented.");
  // }

  constructor(private http: HttpClient, private http1: HttpClient) { this.http = http; }
  public request: any = {

  };

  // Method to fetch Recent Entities

  getRecentEntity(): Observable<any> {
    console.log('Message');
    // console.log(this.request);
    return this.http.get('http://13.235.105.89:8000/api/v1/recentEntities');
    // return this.http.get('http://13.235.105.89:8000/api/v1/recentEntities');
  }

  // Method to fetch Recent Movies

  getRecentMovies(): Observable<any> {
    console.log('Message');
    console.log(this.request);
    return this.http.get('http://13.235.105.89:8000/api/v1/recentMovies');
    // return this.http.get('http://13.235.105.89:8000/api/v1/recentMovies');
  }

  // Method to fetch Recent Restaurants

  getRecentRestaurants(): Observable<any> {
    console.log('Message');
    console.log(this.request);
    return this.http.get('http://13.235.105.89:8000/api/v1/recentRestaurants');
    // return this.http.get('http://13.235.105.89:8000/api/v1/recentRestaurants');
  }

  // Method to fetch Trending Movies

  getTrendingMovies(): Observable<any> {
    console.log('Message');
    // console.log(this.http.get('http://13.235.105.89:8000/api/v1/trendingMovies'));
    return this.http.get('http://13.235.105.89:8000/api/v1/trendingMovies');
  }


  // Method to fetch Trending Restaurants

  getTrendingRestaurants(): Observable<any> {
    console.log('Message');
    // console.log(this.http.get('http://13.235.105.89:8000/api/v1/trendingMovies'));
    return this.http.get('http://13.235.105.89:8000/api/v1/trendingRestaurants');
  }

  // Method to fetch Reccomended Entities

  // getRecommendedData(emailID : string): Observable<any> {
  //   console.log('Message');
  //   console.log(this.request);
  //   console.log(emailID);

  //   // tslint:disable-next-line: max-line-length
  //   console.log(this.http.get('http://13.235.105.89:8010/api/v1/entitiesToReviewer/akhilgrandhi@gmail.com').subscribe((val) => console.log(val)));
  //   console.log(this.http.get('http://13.235.105.89:8010/api/v1/entitiesToReviewer/' + emailID).subscribe((val) => console.log(val)));
  //   console.log( ( (this.http.get('http://13.235.105.89:8010/api/v1/entitiesToReviewer/' + emailID) )
  //    && (this.http.get('http://13.235.105.89:8010/api/v1/entitiesToPoster/' + emailID) ) ).subscribe((val) => console.log(val)));
  //   return (this.http.get('http://13.235.105.89:8010/api/v1/entitiesToReviewer/' + emailID) &&
  //    this.http.get('http://13.235.105.89:8010/api/v1/entitiesToPoster/' + emailID));
  // }

  getRecommendedMovies(emailID: string): Observable<any> {
    return this.http.get('http://13.235.105.89:8010/api/v1/recommendMovies/' + emailID);
  }

  getRecommendedRestuarants(emailID: string): Observable<any> {
    console.log(emailID);
    console.log(this.http.get('http://13.235.105.89:8010/api/v1/recommendRestaurant/' + emailID).subscribe((val) => console.log(val)));
    return this.http.get('http://13.235.105.89:8010/api/v1/recommendRestaurant/' + emailID);
  }

  // Method to Display a Card in full View

  getCard(title: string): Observable<any> {
    console.log('data service');
    //  console.log(this.http.get('http://localhost:8000/api/v1/entity/' + title).subscribe((val) => console.log(val)));
    return this.http.get('http://13.235.105.89:8000/api/v1/entity/' + title + '?emailID=guest&entityCategory=guest');
  }

  // Method to Display the Search Result

  getSearchDetails(title: string, emailID: string, domain: string): Observable<any> {
    console.log('data service');
    console.log(title);
    console.log(emailID);
    console.log(domain);
    return this.http.get(
      'http://13.235.105.89:8000/api/v1/entity/' + title + `?emailID=${emailID}&entityCategory=${domain}`
    );
  }

  // Method to fetch Reviews and  Review Details

  getReviewDetails(title: string): Observable<any> {
    console.log(title);
    return this.http.get('http://13.235.105.89:8025/api/v1/review/' + title);
  }

  // Method  to Fetch the User Entities

  getUserEntities(emailID: string): Observable<any> {
    // tslint:disable-next-line: max-line-length
    // console.log(this.http.get('http://13.235.105.89:8050/api/v1/userentities/akhilgrandhi@gmail.com').subscribe((val) => console.log(val)));
    return this.http.get('http://13.235.105.89:8050/api/v1/userentities/' + emailID);
  }
}
