import { Component, OnInit } from '@angular/core';
import { AddEntityService } from '../add-entity.service';
import { from } from 'rxjs';
import { Router, ActivatedRoute } from '@angular/router';
@Component({
  selector: 'app-add-restaurant',
  templateUrl: './add-restaurant.component.html',
  styleUrls: ['./add-restaurant.component.css']
})
export class AddRestaurantComponent implements OnInit {

  private isUserLoggedIn: any;
  selectedSubCategory = 0;
  category = 'restaurant';
  restaurantTitle: any;
  restaurantLocation: any;
  restaurantDescription: any;
  imageUrl: any;
  domain: any;
  emailId: any;

  subCategory = [];
  brands = [];


  // onSelectCategory(categoryId: number) {
  //   // this.selectedCategory = categoryId;
  //   this.selectedSubCategory = 0;
  //   this.brands = [];
  //   this.subCategory = this.getSubCategory().filter((item) => {
  //     return item.categoryId === Number(categoryId);
  //   });
  // }


  // onSelectSubCategory(subCategoryId: number) {
  //   this.selectedSubCategory = subCategoryId;
  //   this.brands = this.getbrand().filter((item) => {
  //     return item.subCategoryId === Number(subCategoryId);
  //   });
  // }

  // getCategory() {
  //   return [
  //     { id: 1, name: 'Electronics' },
  //     { id: 2, name: 'Restaurants' }
  //   ];
  // }

  getSubCategory() {
    return [
      { id: 1, name: 'Fast Casual Restaurant ' },
      { id: 2, name: 'Casual Dining Restaurant ' },
      { id: 3, name: 'Family Style Restaurant ' },
      { id: 4, name: 'Pop-up Restaurant ' },
      { id: 5, name: 'Buffet Restaurant ' },
      { id: 6, name: 'Bar and Pub' },
      { id: 7, name: 'Fine Dining Restaurant '},
      { id: 8, name: 'Take Away Restaurant'},
    ];
  }



  constructor(private addEntity: AddEntityService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.subCategory = this.getSubCategory();
    this.isUserLoggedIn = this.route.snapshot.paramMap.get('isUserLoggedIn');
    this.domain = this.route.snapshot.paramMap.get('domain');
    this.emailId = this.route.snapshot.paramMap.get('emailId');
  }
  saveEntity(Image: any) {
    this.addEntity.saveEntity(this.category, this.selectedSubCategory, this.restaurantTitle,
       this.restaurantDescription, this.imageUrl, this.emailId, this.restaurantLocation)
      .subscribe((response) => {
        console.log(response);
        if (response) {
          this.router.navigate(['restaurant', {domain: this.domain, isUserLoggedIn: this.isUserLoggedIn, emailId: this.emailId}]);
        }
      }, (err) => {
        console.log(err);
      });
  }


}
