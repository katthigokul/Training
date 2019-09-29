import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AddEntityService {

  constructor(private http: HttpClient ) { }

  public request: any = {
    entityName: '',
    entityCategory: '',
    entitySubCategory: '',
    entityDescription: '',
    entityLocation: '',
    entityImageUrl: '',
    entityPostedBy: ''
      // entity: 'Add entity',
  };

saveEntity(category: any, subCategory: any, title: any, description: any,
           imageUrl: any, emailId: any, restaurantLocation: any ): Observable<any> {
    this.request.entityCategory = category;
    this.request.entitySubCategory = subCategory;
    this.request.entityName = title;
    this.request.entityDescription = description;
    this.request.entityLocation = restaurantLocation;
    this.request.entityImageUrl = imageUrl;
    this.request.entityPostedBy = emailId;
    // const formData: FormData = new FormData();
    // formData.append('Image', fileToUpLoad, fileToUpLoad.name);
    // this.request.entityImageUrl = formData;
    console.log(this.request);
    return this.http.post('http://13.235.105.89:8000/api/v1/entity', this.request);

}

}
